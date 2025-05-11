package ru.nsu.fit.spring_business_process.service;

import java.util.List;

import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import ru.nsu.fit.spring_business_process.entity.BusinessProcess;
import ru.nsu.fit.spring_business_process.repository.BusinessProcessRepository;

@Component
@RequiredArgsConstructor
public class BusinessProcessServiceImpl implements BusinessProcessService {
    private final BusinessProcessRepository businessProcessRepository;

    @Nonnull
    @Override
    public BusinessProcess getById(Long id) {
        return businessProcessRepository.findById(id)
            .orElseThrow(() -> new IllegalStateException("Business process with id %s was not found".formatted(id)));
    }

    @Nonnull
    @Override
    public List<BusinessProcess> findAll() {
        return businessProcessRepository.findAll(Sort.by("id"));
    }
}
