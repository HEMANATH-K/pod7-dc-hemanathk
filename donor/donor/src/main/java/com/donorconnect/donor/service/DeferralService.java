package com.donorconnect.donor.service;

import com.donorconnect.donor.dto.request.DeferralRequest;
import com.donorconnect.donor.dto.response.DeferralResponse;

import java.util.List;

public interface DeferralService {
    DeferralResponse createDeferral(DeferralRequest request);

    List<DeferralResponse> getDeferralsByDonor(String donorId);

    List<DeferralResponse> getActiveDeferrals();

    DeferralResponse expireDeferral(String deferralId);
}
