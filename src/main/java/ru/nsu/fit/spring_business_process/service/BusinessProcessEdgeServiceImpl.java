package ru.nsu.fit.spring_business_process.service;

import java.util.Optional;

import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nsu.fit.spring_business_process.entity.BusinessProcessEdge;
import ru.nsu.fit.spring_business_process.entity.BusinessProcessResult;
import ru.nsu.fit.spring_business_process.entity.BusinessProcessStage;
import ru.nsu.fit.spring_business_process.repository.BusinessProcessEdgeRepository;

@Service
@RequiredArgsConstructor
public class BusinessProcessEdgeServiceImpl implements BusinessProcessEdgeService {
    private final BusinessProcessEdgeRepository businessProcessEdgeRepository;

    @Nonnull
    @Override
    public Optional<BusinessProcessStage> getNextStage(
        BusinessProcessResult result,
        BusinessProcessStage currentStage
    ) {
        return businessProcessEdgeRepository.findByFromAndResult(currentStage, result).map(BusinessProcessEdge::getTo);
    }
}
