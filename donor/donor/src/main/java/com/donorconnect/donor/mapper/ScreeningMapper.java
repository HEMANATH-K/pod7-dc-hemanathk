package com.donorconnect.donor.mapper;

import com.donorconnect.donor.dto.request.ScreeningRequest;
import com.donorconnect.donor.dto.response.ScreeningResponse;
import com.donorconnect.donor.entity.ScreeningRecord;

public class ScreeningMapper {
    public static ScreeningRecord toEntity(ScreeningRequest request) {

        ScreeningRecord record = new ScreeningRecord();

        record.setScreeningDate(request.getScreeningDate());
        record.setVitalsJSON(request.getVitalsJSON());
        record.setQuestionnaireJSON(request.getQuestionnaireJSON());
        record.setClearedFlag(request.isClearedFlag());
        record.setClearedBy(request.getClearedBy());
        record.setNotes(request.getNotes());

        return record;
    }


    public static ScreeningResponse toResponse(ScreeningRecord record) {

        ScreeningResponse response = new ScreeningResponse();

        response.setScreeningId(record.getScreeningId());
        response.setDonorId(record.getDonor().getDonorId());
        response.setScreeningDate(record.getScreeningDate());
        response.setVitalsJSON(record.getVitalsJSON());
        response.setQuestionnaireJSON(record.getQuestionnaireJSON());
        response.setClearedFlag(record.getClearedFlag());
        response.setClearedBy(record.getClearedBy());
        response.setNotes(record.getNotes());
        response.setCreatedAt(record.getCreatedAt());

        return response;
    }
}
