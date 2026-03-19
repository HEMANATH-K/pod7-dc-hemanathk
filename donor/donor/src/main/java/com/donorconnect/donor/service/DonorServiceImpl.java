package com.donorconnect.donor.service;

import com.donorconnect.donor.dto.request.DonorRequest;
import com.donorconnect.donor.dto.response.DonorResponse;
import com.donorconnect.donor.entity.Donor;
import com.donorconnect.donor.enums.DonorStatus;
import com.donorconnect.donor.mapper.DonorMapper;
import com.donorconnect.donor.repository.DonorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DonorServiceImpl implements DonorService {

    private final DonorRepository repository;

    public DonorServiceImpl(DonorRepository repository){
        this.repository = repository;
    }

    private String generateDonorId() {

        long count = repository.count()+1;

        return "DNR-"+ String.format("%06d", count);
    }

    @Override
    public DonorResponse createDonor(DonorRequest request){

        Donor donor = DonorMapper.toEntity(request);

        donor.setDonorId(generateDonorId());
        donor.setStatus(DonorStatus.ACTIVE);

        repository.save(donor);

        return DonorMapper.toResponse(donor);

    }

    @Override
    public DonorResponse getDonor(String donorId){

        Donor donor = repository.findByDonorId(donorId)
                .orElseThrow();

        return DonorMapper.toResponse(donor);

    }
    @Override
    public List<DonorResponse> searchDonorsByName(String name) {

        List<Donor> donors = repository.findByNameContainingIgnoreCase(name);

        return donors.stream()
                .map(DonorMapper::toResponse)
                .toList();
    }
    @Override
    public List<DonorResponse> getAllDonors(){

        List<Donor> donors = repository.findAll();

        return donors.stream()
                .map(DonorMapper::toResponse)
                .toList();
    }

    @Override
    public DonorResponse updateDonor(String donorId,DonorRequest request){

        Donor donor = repository.findByDonorId(donorId)
                .orElseThrow();
        donor.setName(request.getName());
        donor.setDob(request.getDob());
        donor.setGender(request.getGender());
        donor.setBloodGroup(request.getBloodGroup());
        donor.setRhFactor(request.getRhFactor());
        donor.setContactInfo(request.getContactInfo());
        donor.setAddressJSON(request.getAddressJSON());
        donor.setIdCardRef(request.getIdCardRef());
        repository.save(donor);
        return DonorMapper.toResponse(donor);
    }

    @Override
    public void deleteDonor(String donorId){

        Donor donor = repository.findByDonorId(donorId)
                .orElseThrow();
        repository.delete(donor);
    }

    @Override
    public DonorResponse mergeDonors(String sourceDonorId,String targetDonorId){

        Donor source = repository.findByDonorId(sourceDonorId)
                .orElseThrow();
        Donor target = repository.findByDonorId(targetDonorId)
                .orElseThrow();
        //deactivate Duplicate
        source.setStatus(DonorStatus.INACTIVE);
        repository.save(source);
        return DonorMapper.toResponse(target);
    }

    @Override
    public List<DonorResponse> getDonorsByBloodGroup(String bloodGroup){

        List<Donor> donors = repository.findByBloodGroup(bloodGroup);

        return donors.stream()
                .map(DonorMapper::toResponse)
                .toList();
    }

    @Override
    public List<DonorResponse> getDonorsByStatus(DonorStatus status){

        List<Donor> donors = repository.findByStatus(status);

        return donors.stream()
                .map(DonorMapper::toResponse)
                .toList();
    }
}
