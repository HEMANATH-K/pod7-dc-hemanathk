package com.donorconnect.donor.service;

import com.donorconnect.donor.dto.request.ScreeningRequest;
import com.donorconnect.donor.dto.response.ScreeningResponse;

import java.util.List;

public interface ScreeningService {
    ScreeningResponse createScreening(ScreeningRequest request);

    ScreeningResponse getScreening(String screeningId);

    List<ScreeningResponse> getScreeningsByDonorId(String donorId);

    ScreeningResponse updateScreening(String screeningId,ScreeningRequest request);
}
