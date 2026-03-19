package com.donorconnect.donor.dto.request;

import com.donorconnect.donor.enums.DeferralType;
import lombok.Data;

import java.time.LocalDate;

@Data
public class DeferralRequest {
    private String donorId;
    private DeferralType deferralType;
    private String reason;
    private LocalDate startDate;
    private LocalDate endDate;
    private String status;
}
