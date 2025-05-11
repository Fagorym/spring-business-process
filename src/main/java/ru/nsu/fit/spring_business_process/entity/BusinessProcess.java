package ru.nsu.fit.spring_business_process.entity;

import java.util.List;
import java.util.Optional;

import jakarta.annotation.Nonnull;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Entity
@Table(name = "business_process")
@Getter
@Setter
@Accessors(chain = true)
public class BusinessProcess {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "business_process_stage_id")
    private BusinessProcessStage stage;

    @OneToMany(mappedBy = "businessProcess", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BusinessProcessPayload> payloads;

    @Nonnull
    public Optional<BusinessProcessPayload> getPayload(BusinessProcessStage stage) {
        return payloads.stream()
            .filter(payload -> payload.getStage().getId().equals(stage.getId()))
            .findFirst();

    }

    public void addPayload(BusinessProcessPayload businessProcessPayload) {
        payloads.add(businessProcessPayload);
    }
}
