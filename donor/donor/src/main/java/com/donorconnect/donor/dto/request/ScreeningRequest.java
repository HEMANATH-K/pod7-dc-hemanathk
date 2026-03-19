package com.donorconnect.donor.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ScreeningRequest {
    @NotBlank
    private String donorId;
    @NotBlank
    private String vitalsJSON;
    @NotBlank
    private String questionnaireJSON;
    private LocalDateTime screeningDate;
    private String notes;
    private boolean clearedFlag;
    private String clearedBy;
}
