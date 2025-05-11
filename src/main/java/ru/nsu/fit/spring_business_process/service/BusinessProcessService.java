package ru.nsu.fit.spring_business_process.service;

import jakarta.annotation.Nonnull;
import ru.nsu.fit.spring_business_process.entity.BusinessProcess;

public interface BusinessProcessService {
    /**
     * Получить бизнес-процесс по идентификатору.
     *
     * @param id идентификатор бизнес-процесса
     * @return бизнес-процесс
     */
    @Nonnull
    BusinessProcess getById(Long id);
}
