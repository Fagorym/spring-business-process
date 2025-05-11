package ru.nsu.fit.spring_business_process.queue;

import java.time.Duration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import ru.nsu.fit.spring_business_process.executor.StageExecutorPayload;
import ru.nsu.fit.spring_business_process.queue.base.BaseQueueProducer;
import ru.yoomoney.tech.dbqueue.config.QueueShard;
import ru.yoomoney.tech.dbqueue.spring.dao.SpringDatabaseAccessLayer;

@Component
public class BusinessProcessExecutorProducer extends BaseQueueProducer<StageExecutorPayload> {
    public BusinessProcessExecutorProducer(
        ObjectMapper objectMapper,
        QueueShard<SpringDatabaseAccessLayer> queueShard
    ) {
        super(QueueType.BUSINESS_PROCESS_EXECUTION, objectMapper, queueShard);
    }

    public void produce(StageExecutorPayload stageExecutorPayload) {
        produceSingle(stageExecutorPayload, Duration.ZERO);
    }
}
