package ru.nsu.fit.spring_business_process.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.nsu.fit.spring_business_process.entity.BusinessProcessStage;

@Repository
public interface BusinessProcessStageRepository extends JpaRepository<BusinessProcessStage, Long> {
    Optional<BusinessProcessStage> findByName(String name);
}
