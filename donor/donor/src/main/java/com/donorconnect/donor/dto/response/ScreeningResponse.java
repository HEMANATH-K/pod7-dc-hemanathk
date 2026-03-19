package com.donorconnect.donor.dto.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ScreeningResponse {
    private String screeningId;
    private String donorId;
    private LocalDateTime screeningDate;
    private String vitalsJSON;
    private String questionnaireJSON;
    private Boolean clearedFlag;
    private String clearedBy;
    private String notes;
    private LocalDateTime createdAt;

    public ScreeningResponse(){}
}
