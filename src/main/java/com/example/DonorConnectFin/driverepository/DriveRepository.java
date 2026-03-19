package com.example.DonorConnectFin.driverepository;

import com.example.DonorConnectFin.driveentity.Drive;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface DriveRepository extends JpaRepository<Drive, Long> {

    List<Drive> findByScheduledDateAfter(LocalDate date);
}
