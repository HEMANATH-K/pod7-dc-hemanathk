package com.donorconnect.donor.dto.response;

import com.donorconnect.donor.enums.DonorStatus;
import com.donorconnect.donor.enums.DonorType;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class DonorResponse {
    private String donorId;
    private String name;
    private LocalDate dob;
    private String gender;
    private String bloodGroup;
    private String rhFactor;
    private String contactInfo;
    private String addressJSON;
    private DonorType donorType;
    private DonorStatus status;
    private String idCardRef;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
