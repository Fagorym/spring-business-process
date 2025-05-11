package ru.nsu.fit.spring_business_process.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nsu.fit.spring_business_process.entity.BusinessProcess;

public interface BusinessProcessRepository extends JpaRepository<BusinessProcess, Long> {
}
