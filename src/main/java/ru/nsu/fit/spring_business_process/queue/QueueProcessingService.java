package ru.nsu.fit.spring_business_process.queue;

import jakarta.annotation.Nonnull;
import ru.yoomoney.tech.dbqueue.api.TaskExecutionResult;

public interface QueueProcessingService<T> {
    /**
     * Выполнить обработку задачи.
     *
     * @param payload полезная нагрузка задачи
     * @return результат выполнения задачи
     */
    @Nonnull
    TaskExecutionResult process(T payload);
}
