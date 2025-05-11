package ru.nsu.fit.spring_business_process.queue.base;

import java.time.Duration;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.Nonnull;
import ru.nsu.fit.spring_business_process.configuration.QueueConfiguration;
import ru.nsu.fit.spring_business_process.queue.QueueType;
import ru.nsu.fit.spring_business_process.utils.PayloadTransformerUtils;
import ru.yoomoney.tech.dbqueue.api.EnqueueParams;
import ru.yoomoney.tech.dbqueue.api.EnqueueResult;
import ru.yoomoney.tech.dbqueue.api.QueueProducer;
import ru.yoomoney.tech.dbqueue.api.TaskPayloadTransformer;
import ru.yoomoney.tech.dbqueue.config.QueueShard;
import ru.yoomoney.tech.dbqueue.settings.QueueConfig;
import ru.yoomoney.tech.dbqueue.spring.dao.SpringDatabaseAccessLayer;

public class BaseQueueProducer<T> implements QueueProducer<T> {
    private final ObjectMapper objectMapper;
    private final Class<T> payloadClass;
    private final QueueShard<SpringDatabaseAccessLayer> queueShard;
    private final QueueConfig queueConfig;

    @SuppressWarnings("unchecked")
    public BaseQueueProducer(
        QueueType queueType,
        ObjectMapper objectMapper,
        QueueShard<SpringDatabaseAccessLayer> queueShard
    ) {
        this.objectMapper = objectMapper;
        this.payloadClass = (Class<T>) queueType.getPayloadClass();
        this.queueShard = queueShard;
        this.queueConfig = QueueConfiguration.fromType(queueType);
    }

    public void produceSingle(T payload, Duration executionDelay) {
        enqueue(new EnqueueParams<T>()
            .withPayload(payload)
            .withExecutionDelay(executionDelay));
    }

    @Nonnull
    @Override
    public EnqueueResult enqueue(EnqueueParams<T> enqueueParams) {
        EnqueueParams<String> stringEnqueueParams = new EnqueueParams<String>()
            .withPayload(getPayloadTransformer().fromObject(enqueueParams.getPayload()))
            .withExtData(enqueueParams.getExtData())
            .withExecutionDelay(enqueueParams.getExecutionDelay());
        long taskId = queueShard.getDatabaseAccessLayer().transact(
            () -> queueShard.getDatabaseAccessLayer().getQueueDao()
                .enqueue(queueConfig.getLocation(), stringEnqueueParams));
        return new EnqueueResult(queueShard.getShardId(), taskId);
    }

    @Nonnull
    @Override
    public TaskPayloadTransformer<T> getPayloadTransformer() {
        return PayloadTransformerUtils.getPayloadTransformer(payloadClass, objectMapper);
    }
}
