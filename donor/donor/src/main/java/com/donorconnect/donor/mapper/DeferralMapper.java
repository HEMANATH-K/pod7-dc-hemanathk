package com.donorconnect.donor.mapper;

import com.donorconnect.donor.dto.request.DeferralRequest;
import com.donorconnect.donor.dto.response.DeferralResponse;
import com.donorconnect.donor.entity.Deferral;

public class DeferralMapper {
    public static Deferral toEntity(DeferralRequest req){

        Deferral def = new Deferral();

        def.setDeferralType(req.getDeferralType());
        def.setReason(req.getReason());
        def.setStartDate(req.getStartDate());
        def.setEndDate(req.getEndDate());
        def.setStatus(req.getStatus());

        return def;
    }

    public static DeferralResponse toResponse(Deferral def){

        DeferralResponse res = new DeferralResponse();

        res.setDeferralId(def.getDeferralId());
        res.setDonorId(def.getDonor().getDonorId());
        res.setDeferralType(def.getDeferralType());
        res.setReason(def.getReason());
        res.setStartDate(def.getStartDate());
        res.setEndDate(def.getEndDate());
        res.setStatus(def.getStatus());
        res.setCreatedAt(def.getCreatedAt());

        return res;
    }
}
