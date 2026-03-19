package com.donorconnect.donor.entity;

import com.donorconnect.donor.enums.DonorStatus;
import com.donorconnect.donor.enums.DonorType;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name="Donor")
public class Donor {
    @Column(name = "donor_id",unique = true, nullable = false)
    @Id
    private String donorId;
    private String name;
    private LocalDate dob;
    private String gender;
    private String bloodGroup;
    private String rhFactor;
    private String contactInfo;

    @Column(columnDefinition = "TEXT")
    private String addressJSON;

    @Enumerated(EnumType.STRING)
    private DonorType donorType;

    @Enumerated(EnumType.STRING)
    private DonorStatus status;

    private String idCardRef;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate(){
        updatedAt = LocalDateTime.now();
    }
}
