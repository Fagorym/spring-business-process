package ru.nsu.fit.spring_business_process.service;

import java.util.Optional;

import jakarta.annotation.Nonnull;
import ru.nsu.fit.spring_business_process.entity.BusinessProcessResult;
import ru.nsu.fit.spring_business_process.entity.BusinessProcessStage;

public interface BusinessProcessEdgeService {
    /**
     * Получить следующую стадию бизнес-процесса.
     *
     * @param result       результат исполнения
     * @param currentStage текущая стадия
     * @return следующая стадия или {@code Optional.empty()}
     */
    @Nonnull
    Optional<BusinessProcessStage> getNextStage(BusinessProcessResult result, BusinessProcessStage currentStage);
}
