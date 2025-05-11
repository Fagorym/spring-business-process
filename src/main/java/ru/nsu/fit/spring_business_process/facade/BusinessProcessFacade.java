package ru.nsu.fit.spring_business_process.facade;

public interface BusinessProcessFacade {
    /**
     * Начать исполнение бизнес-процесса.
     */
    <T> void initBusinessProcess(T initialPayload, String stageName);
}
