package com.donorconnect.donor.dto.response;

import com.donorconnect.donor.enums.DeferralType;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class DeferralResponse {
    private String deferralId;
    private String donorId;
    private DeferralType deferralType;
    private String reason;
    private LocalDate startDate;
    private LocalDate endDate;
    private String status;
    private LocalDateTime createdAt;
}
