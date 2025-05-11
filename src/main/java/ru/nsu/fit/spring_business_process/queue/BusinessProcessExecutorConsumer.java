package ru.nsu.fit.spring_business_process.queue;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import ru.nsu.fit.spring_business_process.executor.StageExecutorPayload;
import ru.nsu.fit.spring_business_process.queue.base.BaseQueueConsumer;

@Component
public class BusinessProcessExecutorConsumer extends BaseQueueConsumer<StageExecutorPayload> {

    public BusinessProcessExecutorConsumer(
        ObjectMapper objectMapper,
        BusinessProcessExecutor processingService
    ) {
        super(QueueType.BUSINESS_PROCESS_EXECUTION, objectMapper, processingService);
    }
}
