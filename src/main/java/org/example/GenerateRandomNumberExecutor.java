package org.example;

import java.util.Random;

import jakarta.annotation.Nonnull;
import org.springframework.stereotype.Component;
import ru.nsu.fit.spring_business_process.entity.BusinessProcess;
import ru.nsu.fit.spring_business_process.entity.BusinessProcessResultData;
import ru.nsu.fit.spring_business_process.executor.StageExecutor;

@Component
public class GenerateRandomNumberExecutor implements StageExecutor<EmptyPayload> {
    @Nonnull
    @Override
    public String getStageName() {
        return "GENERATE_RANDOM_NUMBER";
    }

    @Nonnull
    @Override
    public Class<EmptyPayload> getPayloadClass() {
        return EmptyPayload.class;
    }

    @Nonnull
    @Override
    public BusinessProcessResultData execute(BusinessProcess businessProcess, EmptyPayload payload) {
        return BusinessProcessResultData.success(new RandomNumberPayload(new Random().nextInt()));
    }
}
