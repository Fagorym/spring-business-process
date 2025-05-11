package ru.nsu.fit.spring_business_process.service;

import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.nsu.fit.spring_business_process.entity.BusinessProcessStage;
import ru.nsu.fit.spring_business_process.repository.BusinessProcessStageRepository;

@Component
@RequiredArgsConstructor
public class BusinessProcessStageServiceImpl implements BusinessProcessStageService {
    private final BusinessProcessStageRepository businessProcessStageRepository;

    @Nonnull
    @Override
    public BusinessProcessStage getByName(String name) {
        return businessProcessStageRepository.findByName(name)
            .orElseThrow(() -> new IllegalArgumentException("Not found stage with name = " + name));
    }
}
