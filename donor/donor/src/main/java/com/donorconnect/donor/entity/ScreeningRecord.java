package com.donorconnect.donor.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data

@Entity
@Table(name="ScreeningRecord")
public class ScreeningRecord {
    @Id
    private String screeningId;
    @ManyToOne
    @JoinColumn(name="donor_id")
    private Donor donor;
    private LocalDateTime screeningDate;
    @Column(columnDefinition = "TEXT")
    private String vitalsJSON;
    @Column(columnDefinition = "TEXT")
    private String questionnaireJSON;
    private Boolean clearedFlag;
    private String clearedBy;
    private String notes;
    private LocalDateTime createdAt;

    @PrePersist
    public void prePersist(){
        createdAt = LocalDateTime.now();
    }
}
