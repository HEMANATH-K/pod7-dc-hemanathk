package com.donorconnect.donor.repository;

import com.donorconnect.donor.entity.ScreeningRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScreeningRecordRepository extends JpaRepository<ScreeningRecord,String> {
    List<ScreeningRecord> findByDonor_DonorIdOrderByScreeningDateDesc(String donorId);

    List<ScreeningRecord> findByDonor_DonorId(String donorId);
}
