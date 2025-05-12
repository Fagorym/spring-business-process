package ru.nsu.fit.spring_business_process.executor;

import jakarta.annotation.Nonnull;
import ru.nsu.fit.spring_business_process.entity.BusinessProcess;
import ru.nsu.fit.spring_business_process.entity.BusinessProcessResultData;

public interface StageExecutor<P> {
    /**
     * Получить название фазы исполнения.
     *
     * @return название фазы исполнения
     */
    @Nonnull
    String getStageName();

    /**
     * Получить класс пейлоада.
     * @return класс пейлоада
     */
    @Nonnull
    Class<P> getPayloadClass();

    /**
     * Исполнить бизнес-процесс.
     *
     * @param payload         данные для обработки события
     * @return событие перехода в следующий этап
     */
    @Nonnull
    BusinessProcessResultData execute(P payload);
}
