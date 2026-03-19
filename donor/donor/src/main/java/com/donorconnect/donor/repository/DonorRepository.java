package com.donorconnect.donor.repository;

import com.donorconnect.donor.entity.Donor;
import com.donorconnect.donor.enums.DonorStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface DonorRepository extends JpaRepository<Donor,String> {
    Optional<Donor> findByDonorId(String donorId);

    List<Donor> findByNameContainingIgnoreCase(String name);

    Optional<Donor> findByNameAndDobAndContactInfo(String name, LocalDate dob, String contactInfo);

    List<Donor> findByBloodGroup(String bloodGroup);

    List<Donor> findByStatus(DonorStatus status);
}
