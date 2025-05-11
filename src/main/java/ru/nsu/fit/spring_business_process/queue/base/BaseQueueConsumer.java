package ru.nsu.fit.spring_business_process.queue.base;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.Nonnull;
import ru.nsu.fit.spring_business_process.configuration.QueueConfiguration;
import ru.nsu.fit.spring_business_process.queue.QueueProcessingService;
import ru.nsu.fit.spring_business_process.queue.QueueType;
import ru.nsu.fit.spring_business_process.utils.PayloadTransformerUtils;
import ru.yoomoney.tech.dbqueue.api.QueueConsumer;
import ru.yoomoney.tech.dbqueue.api.Task;
import ru.yoomoney.tech.dbqueue.api.TaskExecutionResult;
import ru.yoomoney.tech.dbqueue.api.TaskPayloadTransformer;
import ru.yoomoney.tech.dbqueue.settings.QueueConfig;

public class BaseQueueConsumer<T> implements QueueConsumer<T> {
    private final QueueConfig queueConfig;
    private final Class<T> payloadClass;
    private final ObjectMapper objectMapper;
    private final QueueProcessingService<T> processingService;

    @SuppressWarnings("unchecked")
    public BaseQueueConsumer(
        QueueType queueType,
        ObjectMapper objectMapper,
        QueueProcessingService<T> processingService
    ) {
        this.queueConfig = QueueConfiguration.fromType(queueType);
        this.payloadClass = (Class<T>) queueType.getPayloadClass();
        this.objectMapper = objectMapper;
        this.processingService = processingService;
    }

    @Nonnull
    @Override
    public TaskExecutionResult execute(Task<T> task) {
        return processingService.process(task.getPayloadOrThrow());
    }

    @Nonnull
    @Override
    public QueueConfig getQueueConfig() {
        return queueConfig;
    }

    @Nonnull
    @Override
    public TaskPayloadTransformer<T> getPayloadTransformer() {
        return PayloadTransformerUtils.getPayloadTransformer(payloadClass, objectMapper);
    }
}
