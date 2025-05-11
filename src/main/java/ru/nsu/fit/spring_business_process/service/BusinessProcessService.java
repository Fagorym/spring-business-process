package ru.nsu.fit.spring_business_process.service;

import java.util.List;

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

    /**
     * Сохранить бизнес-процесс.
     *
     * @param businessProcess бизнес-процесс
     */
    void save(BusinessProcess businessProcess);

    /**
     * Получить все бизнес-процессы.
     *
     * @return список бизнес-процессов.
     */
    @Nonnull
    List<BusinessProcess> findAll();
}
