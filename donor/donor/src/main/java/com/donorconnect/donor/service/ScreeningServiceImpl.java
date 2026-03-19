package com.donorconnect.donor.service;

import com.donorconnect.donor.dto.request.ScreeningRequest;
import com.donorconnect.donor.dto.response.ScreeningResponse;
import com.donorconnect.donor.entity.Deferral;
import com.donorconnect.donor.entity.Donor;
import com.donorconnect.donor.entity.ScreeningRecord;
import com.donorconnect.donor.enums.DeferralType;
import com.donorconnect.donor.enums.DonorStatus;
import com.donorconnect.donor.mapper.ScreeningMapper;
import com.donorconnect.donor.repository.DeferralRepository;
import com.donorconnect.donor.repository.DonorRepository;
import com.donorconnect.donor.repository.ScreeningRecordRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ScreeningServiceImpl implements ScreeningService{
    private final ScreeningRecordRepository screeningRepository;
    private final DonorRepository donorRepository;
    private final DeferralRepository deferralRepository;

    public ScreeningServiceImpl(ScreeningRecordRepository screeningRepository,
                                DonorRepository donorRepository,
                                DeferralRepository deferralRepository) {
        this.screeningRepository = screeningRepository;
        this.donorRepository = donorRepository;
        this.deferralRepository = deferralRepository;

    }

    @Override
    public ScreeningResponse createScreening(ScreeningRequest request) {

        Donor donor = donorRepository.findByDonorId(request.getDonorId())
                .orElseThrow(() -> new RuntimeException("Donor not found"));

        ScreeningRecord record = ScreeningMapper.toEntity(request);

        record.setScreeningId(generateScreeningId());
        record.setDonor(donor);

        screeningRepository.save(record);

//        If donor is not eligible → create deferral

        if (!record.getClearedFlag()) {

            Deferral def = new Deferral();

            def.setDeferralId(generateDeferralId());
            def.setDonor(donor);
            def.setDeferralType(DeferralType.TEMPORARY);
            def.setReason(record.getNotes());
            def.setStartDate(LocalDate.now());
            def.setEndDate(LocalDate.now().plusMonths(3));
            def.setStatus("ACTIVE");

            deferralRepository.save(def);

            // Update donor status
            donor.setStatus(DonorStatus.DEFERRED);
            donorRepository.save(donor);
        }

        return ScreeningMapper.toResponse(record);
    }

    @Override
    public ScreeningResponse getScreening(String screeningId) {

        ScreeningRecord record = screeningRepository.findById(screeningId)
                .orElseThrow(() -> new RuntimeException("Screening not found"));

        return ScreeningMapper.toResponse(record);
    }

    @Override
    public List<ScreeningResponse> getScreeningsByDonorId(String donorId){

        List<ScreeningRecord> records = screeningRepository.findByDonor_DonorId(donorId);

        return records.stream()
                .map(ScreeningMapper::toResponse)
                .toList();
    }

    @Override
    public ScreeningResponse updateScreening(String screeningId, ScreeningRequest request) {

        ScreeningRecord record = screeningRepository.findById(screeningId)
                .orElseThrow(() -> new RuntimeException("Screening not found"));

        record.setVitalsJSON(request.getVitalsJSON());
        record.setQuestionnaireJSON(request.getQuestionnaireJSON());
        record.setClearedFlag(request.isClearedFlag());
        record.setClearedBy(request.getClearedBy());
        record.setNotes(request.getNotes());

        screeningRepository.save(record);

        return ScreeningMapper.toResponse(record);
    }

    private String generateScreeningId() {

        long count = screeningRepository.count() + 1;

        return "SCR-" + String.format("%06d", count);
    }

    private String generateDeferralId() {

        long count = deferralRepository.count() + 1;

        return "DFR-" + String.format("%06d", count);
    }
}
