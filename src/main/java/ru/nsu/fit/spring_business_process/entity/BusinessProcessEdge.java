package ru.nsu.fit.spring_business_process.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;

@Entity
@Table(name = "business_process_edge")
@Getter
public class BusinessProcessEdge {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "from_stage_id")
    private BusinessProcessStage from;

    @Column
    @Enumerated(EnumType.STRING)
    private BusinessProcessResult result;

    @ManyToOne
    @JoinColumn(name = "to_stage_id")
    private BusinessProcessStage to;
}
