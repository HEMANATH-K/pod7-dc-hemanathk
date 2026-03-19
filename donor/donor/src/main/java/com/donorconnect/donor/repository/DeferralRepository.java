package com.donorconnect.donor.repository;

import com.donorconnect.donor.entity.Deferral;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DeferralRepository extends JpaRepository<Deferral,String> {
    List<Deferral> findByDonor_DonorId(String donorId);

    List<Deferral> findByStatus(String status);
}
