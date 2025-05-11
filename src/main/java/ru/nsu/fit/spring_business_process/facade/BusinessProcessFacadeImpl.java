package ru.nsu.fit.spring_business_process.facade;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import ru.nsu.fit.spring_business_process.entity.BusinessProcess;
import ru.nsu.fit.spring_business_process.entity.BusinessProcessPayload;
import ru.nsu.fit.spring_business_process.entity.BusinessProcessStage;
import ru.nsu.fit.spring_business_process.executor.StageExecutorPayload;
import ru.nsu.fit.spring_business_process.queue.BusinessProcessExecutorProducer;
import ru.nsu.fit.spring_business_process.service.BusinessProcessStageService;

@RequiredArgsConstructor
public class BusinessProcessFacadeImpl implements BusinessProcessFacade {
    private final ObjectMapper objectMapper;
    private final BusinessProcessStageService businessProcessStageService;
    private final BusinessProcessExecutorProducer producer;

    @Override
    @SneakyThrows
    public <T> void initBusinessProcess(T initialPayload, String stageName) {
        BusinessProcess businessProcess = new BusinessProcess();
        BusinessProcessStage initialStage = businessProcessStageService.getByName(stageName);
        businessProcess.setStage(initialStage);
        businessProcess.addPayload(
            new BusinessProcessPayload()
                .setPayload(objectMapper.writeValueAsString(initialPayload))
                .setStage(initialStage)
        );
        producer.produce(new StageExecutorPayload(businessProcess.getId()));
    }
}
