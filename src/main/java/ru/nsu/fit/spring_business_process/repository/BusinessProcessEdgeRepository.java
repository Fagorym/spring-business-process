package ru.nsu.fit.spring_business_process.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.nsu.fit.spring_business_process.entity.BusinessProcessEdge;
import ru.nsu.fit.spring_business_process.entity.BusinessProcessResult;
import ru.nsu.fit.spring_business_process.entity.BusinessProcessStage;

@Repository
public interface BusinessProcessEdgeRepository extends JpaRepository<BusinessProcessEdge, Long> {
    Optional<BusinessProcessEdge> findByFromAndResult(BusinessProcessStage from, BusinessProcessResult result);
}
