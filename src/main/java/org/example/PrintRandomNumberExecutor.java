package org.example;

import jakarta.annotation.Nonnull;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import ru.nsu.fit.spring_business_process.entity.BusinessProcess;
import ru.nsu.fit.spring_business_process.entity.BusinessProcessResultData;
import ru.nsu.fit.spring_business_process.executor.StageExecutor;

@Slf4j
@Component
public class PrintRandomNumberExecutor implements StageExecutor<RandomNumberPayload> {
    @NotNull
    @Override
    public String getStageName() {
        return "PRINT_RANDOM_NUMBER";
    }

    @Nonnull
    @Override
    public Class<RandomNumberPayload> getPayloadClass() {
        return RandomNumberPayload.class;
    }

    @Nonnull
    @Override
    public BusinessProcessResultData execute(BusinessProcess businessProcess, RandomNumberPayload payload) {
       log.info("Random number was {}", payload.getRandomNumber());
       return BusinessProcessResultData.success(null);
    }
}
