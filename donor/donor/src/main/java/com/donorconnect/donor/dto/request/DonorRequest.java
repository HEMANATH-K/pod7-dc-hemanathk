package com.donorconnect.donor.dto.request;

import com.donorconnect.donor.enums.DonorType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class DonorRequest {
    @NotBlank
    private String name;

    @NotNull
    private LocalDate dob;

    private String gender;

    private String bloodGroup;

    private String rhFactor;

    private String contactInfo;

    private String addressJSON;

    private DonorType donorType;

    private String idCardRef;
}
