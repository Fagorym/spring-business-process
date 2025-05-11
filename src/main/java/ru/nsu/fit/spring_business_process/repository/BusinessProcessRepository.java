package ru.nsu.fit.spring_business_process.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.nsu.fit.spring_business_process.entity.BusinessProcess;

@Repository
public interface BusinessProcessRepository extends JpaRepository<BusinessProcess, Long> {
}
