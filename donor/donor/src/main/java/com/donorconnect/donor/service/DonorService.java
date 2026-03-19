package com.donorconnect.donor.service;

import com.donorconnect.donor.dto.request.DonorRequest;
import com.donorconnect.donor.dto.response.DonorResponse;
import com.donorconnect.donor.enums.DonorStatus;

import java.util.List;

public interface DonorService {
    DonorResponse createDonor(DonorRequest request);

    DonorResponse getDonor(String donorId);

    List<DonorResponse> searchDonorsByName(String name);

    List<DonorResponse> getAllDonors();

    DonorResponse updateDonor(String donorId, DonorRequest request);

    void deleteDonor(String donorId);

    DonorResponse mergeDonors(String sourceDonorId,String targetDonorId);

    List<DonorResponse> getDonorsByBloodGroup(String bloodGroup);

    List<DonorResponse> getDonorsByStatus(DonorStatus status);
}
