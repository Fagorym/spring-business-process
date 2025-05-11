package ru.nsu.fit.spring_business_process.queue;

import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.nsu.fit.spring_business_process.entity.BusinessProcess;
import ru.nsu.fit.spring_business_process.entity.BusinessProcessPayload;
import ru.nsu.fit.spring_business_process.entity.BusinessProcessResultData;
import ru.nsu.fit.spring_business_process.entity.BusinessProcessStage;
import ru.nsu.fit.spring_business_process.executor.StageExecutor;
import ru.nsu.fit.spring_business_process.executor.StageExecutorPayload;
import ru.nsu.fit.spring_business_process.service.BusinessProcessEdgeService;
import ru.nsu.fit.spring_business_process.service.BusinessProcessService;
import ru.yoomoney.tech.dbqueue.api.TaskExecutionResult;

@Slf4j
@Component
@RequiredArgsConstructor
public class BusinessProcessExecutor implements QueueProcessingService<StageExecutorPayload> {
    private final List<StageExecutor<?>> executors;
    private final BusinessProcessService businessProcessService;
    private final BusinessProcessEdgeService businessProcessEdgeService;
    private final BusinessProcessExecutorProducer producer;
    private final ObjectMapper objectMapper;

    @Nonnull
    @SneakyThrows
    @Override
    public TaskExecutionResult process(StageExecutorPayload payload) {
        log.info("Getting business process {} from db", payload.getBusinessProcessId());
        BusinessProcess businessProcess = businessProcessService.getById(payload.getBusinessProcessId());

        StageExecutor matchingExecutor = executors.stream()
            .filter(executor -> executor.getStageName().equals(businessProcess.getStage().getName()))
            .findFirst()
            .orElse(null);

        if (matchingExecutor == null) {
            log.warn("No executor found for stage {}", businessProcess.getStage().getName());
            return TaskExecutionResult.finish();
        }

        BusinessProcessResultData resultData = matchingExecutor.execute(
            businessProcess,
            objectMapper.readValue(
                businessProcess.getPayload(businessProcess.getStage())
                    .map(BusinessProcessPayload::getPayload)
                    .orElse(null),
                matchingExecutor.getPayloadClass()
            )
        );

        BusinessProcessStage nextStage = businessProcessEdgeService.getNextStage(
                resultData.getResult(),
                businessProcess.getStage()
            )
            .orElse(null);

        if (nextStage == null) {
            log.info("Final stage reached, no enqueued tasks after this");
            return TaskExecutionResult.finish();
        }

        businessProcess.setStage(nextStage);
        businessProcess.addPayload(
            new BusinessProcessPayload()
                .setPayload(objectMapper.writeValueAsString(resultData.getNextPayload()))
                .setStage(nextStage)
        );
        businessProcessService.save(businessProcess);
        producer.produce(new StageExecutorPayload(businessProcess.getId()));
        return TaskExecutionResult.finish();
    }
}
