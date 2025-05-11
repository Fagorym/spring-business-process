package ru.nsu.fit.spring_business_process.queue;

import lombok.Getter;
import ru.nsu.fit.spring_business_process.executor.StageExecutorPayload;

@Getter
public enum QueueType {
    BUSINESS_PROCESS_EXECUTION("BUSINESS_PROCESS_EXECUTION", StageExecutorPayload.class),
    ;

    private final String queueId;
    private final Class<?> payloadClass;

    QueueType(String queueId, Class<?> payloadClass) {
        this.queueId = queueId;
        this.payloadClass = payloadClass;
    }
}
