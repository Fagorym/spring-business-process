package ru.nsu.fit.spring_business_process.service;

import jakarta.annotation.Nonnull;
import ru.nsu.fit.spring_business_process.entity.BusinessProcessStage;

public interface BusinessProcessStageService {
    /**
     * Получить стадию по названию.
     *
     * @param name название стадии исполнения
     * @return стадия исполнения
     */
    @Nonnull
    BusinessProcessStage getByName(String name);
}
