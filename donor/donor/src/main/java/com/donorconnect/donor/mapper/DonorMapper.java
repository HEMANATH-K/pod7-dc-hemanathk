package com.donorconnect.donor.mapper;

import com.donorconnect.donor.dto.request.DonorRequest;
import com.donorconnect.donor.dto.response.DonorResponse;
import com.donorconnect.donor.entity.Donor;

public class DonorMapper {
    public static Donor toEntity(DonorRequest request) {

        Donor donor = new Donor();

        donor.setName(request.getName());
        donor.setDob(request.getDob());
        donor.setGender(request.getGender());
        donor.setBloodGroup(request.getBloodGroup());
        donor.setRhFactor(request.getRhFactor());
        donor.setContactInfo(request.getContactInfo());
        donor.setAddressJSON(request.getAddressJSON());
        donor.setDonorType(request.getDonorType());
        donor.setIdCardRef(request.getIdCardRef());

        return donor;
    }

    public static DonorResponse toResponse(Donor donor) {

        DonorResponse response = new DonorResponse();

        response.setDonorId(donor.getDonorId());
        response.setName(donor.getName());
        response.setDob(donor.getDob());
        response.setGender(donor.getGender());
        response.setBloodGroup(donor.getBloodGroup());
        response.setRhFactor(donor.getRhFactor());
        response.setContactInfo(donor.getContactInfo());
        response.setAddressJSON(donor.getAddressJSON());
        response.setDonorType(donor.getDonorType());
        response.setStatus(donor.getStatus());
        response.setIdCardRef(donor.getIdCardRef());
        response.setCreatedAt(donor.getCreatedAt());
        response.setUpdatedAt(donor.getUpdatedAt());

        return response;
    }
}
