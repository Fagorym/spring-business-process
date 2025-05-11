package org.example;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.nsu.fit.spring_business_process.facade.BusinessProcessFacade;

@Slf4j
@Component
@RequiredArgsConstructor
public class RandomNumberExecutor {
    private final BusinessProcessFacade businessProcessFacade;

    @Scheduled(cron = "0/30 * * * * *")
    void execute() {
        log.info("Init random number business process");
        businessProcessFacade.initBusinessProcess(null, "GENERATE_RANDOM_NUMBER");
    }
}
