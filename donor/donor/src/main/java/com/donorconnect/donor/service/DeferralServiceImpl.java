package com.donorconnect.donor.service;

import com.donorconnect.donor.dto.request.DeferralRequest;
import com.donorconnect.donor.dto.response.DeferralResponse;
import com.donorconnect.donor.entity.Deferral;
import com.donorconnect.donor.entity.Donor;
import com.donorconnect.donor.enums.DeferralType;
import com.donorconnect.donor.enums.DonorStatus;
import com.donorconnect.donor.mapper.DeferralMapper;
import com.donorconnect.donor.repository.DeferralRepository;
import com.donorconnect.donor.repository.DonorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DeferralServiceImpl implements DeferralService {
    private final DeferralRepository deferralRepository;
    private final DonorRepository donorRepository;

    public DeferralServiceImpl(DeferralRepository deferralRepository,
                               DonorRepository donorRepository){
        this.deferralRepository = deferralRepository;
        this.donorRepository = donorRepository;
    }

    @Override
    public DeferralResponse createDeferral(DeferralRequest request){
        Donor donor = donorRepository.findByDonorId(request.getDonorId())
                .orElseThrow(()-> new RuntimeException("Donor not found"));
        Deferral def = DeferralMapper.toEntity(request);
        def.setDonor(donor);

        def.setDeferralId("DFR-"+String.format("%06d",deferralRepository.count()+1));

        deferralRepository.save(def);
        donor.setStatus(DonorStatus.DEFERRED);
        donorRepository.save(donor);

        return DeferralMapper.toResponse(def);
    }

    @Override
    public List<DeferralResponse> getDeferralsByDonor(String donorId) {
        List<Deferral> list = deferralRepository.findByDonor_DonorId(donorId);

        return list.stream()
                .map(DeferralMapper::toResponse)
                .collect(Collectors.toList());

    }

    @Override
    public List<DeferralResponse> getActiveDeferrals() {
        List<Deferral> list = deferralRepository.findByStatus("ACTIVE");

        return list.stream()
                .map(DeferralMapper::toResponse)
                .collect(Collectors.toList());

    }

    @Override
    public DeferralResponse expireDeferral(String deferralId) {

        Deferral def = deferralRepository.findById(deferralId)
                .orElseThrow(()->new RuntimeException("Deferral not found"));

        def.setStatus("EXPIRED");

        // if temporary deferral, activate donor again
        if(def.getDeferralType() == DeferralType.TEMPORARY){

            Donor donor = def.getDonor();
            donor.setStatus(DonorStatus.ACTIVE);
            donorRepository.save(donor);
        }

        deferralRepository.save(def);

        return DeferralMapper.toResponse(def);

    }
}
