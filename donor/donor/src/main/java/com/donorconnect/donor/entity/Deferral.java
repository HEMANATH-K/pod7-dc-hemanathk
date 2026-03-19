package com.donorconnect.donor.entity;

import com.donorconnect.donor.enums.DeferralType;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data

@Entity
@Table(name="deferrals")
public class Deferral {
    @Id
    @Column(name="deferral_id")
    private String deferralId;

    @ManyToOne
    @JoinColumn(name="donor_id")
    private Donor donor;

    @Enumerated(EnumType.STRING)
    private DeferralType deferralType;

    private String reason;

    private LocalDate startDate;

    private LocalDate endDate;

    private String status;

    private LocalDateTime createdAt;

    @PrePersist
    public void prePersist(){
        createdAt = LocalDateTime.now();
    }
}
