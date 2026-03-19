package com.example.DonorConnectFin.appointmentrepository;

import com.example.DonorConnectFin.appointmententity.DonationAppointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<DonationAppointment,Long> {
    List<DonationAppointment> findByDonorId(Long donorId);
    List<DonationAppointment> findByDateTimeBetween(LocalDateTime start,LocalDateTime end);
    boolean existsByDonorIdAndDateTimeBetween(Long donorId,LocalDateTime start, LocalDateTime end);
    int countByDrive_DriveId(Long driveId);
}
