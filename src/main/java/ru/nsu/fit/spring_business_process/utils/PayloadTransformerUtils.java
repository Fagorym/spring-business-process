package ru.nsu.fit.spring_business_process.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.Nonnull;
import lombok.experimental.UtilityClass;
import ru.yoomoney.tech.dbqueue.api.TaskPayloadTransformer;

@UtilityClass
public class PayloadTransformerUtils {
    @Nonnull
    public <T> TaskPayloadTransformer<T> getPayloadTransformer(Class<T> payloadClass, ObjectMapper objectMapper) {
        return new TaskPayloadTransformer<>() {
            @Nonnull
            @Override
            public T toObject(String payload) {
                try {
                    return objectMapper.readValue(payload, payloadClass);
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            }

            @Nonnull
            @Override
            public String fromObject(T payload) {
                try {
                    return objectMapper.writeValueAsString(payload);
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            }
        };
    }
}
