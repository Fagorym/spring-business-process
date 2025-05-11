package ru.nsu.fit.spring_business_process.entity;

import jakarta.annotation.Nonnull;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Entity
@Table(name = "business_process_payload")
@Getter
@Setter
@Accessors(chain = true)
public class BusinessProcessPayload {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "business_process_stage_id")
    private BusinessProcessStage stage;

    @ManyToOne
    @JoinColumn(name = "business_process_id")
    private BusinessProcess businessProcess;

    @Nonnull
    private String payload;
}
