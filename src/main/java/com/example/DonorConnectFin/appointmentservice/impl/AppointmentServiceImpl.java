package com.example.DonorConnectFin.appointmentservice.impl;

import com.example.DonorConnectFin.appointmentdto.AppointmentRequestDTO;
import com.example.DonorConnectFin.appointmentdto.AppointmentResponseDTO;
import com.example.DonorConnectFin.appointmententity.DonationAppointment;
import com.example.DonorConnectFin.appointmentenums.AppointmentStatus;
import com.example.DonorConnectFin.appointmentrepository.AppointmentRepository;
import com.example.DonorConnectFin.appointmentservice.AppointmentService;
import com.example.DonorConnectFin.drivedto.DriveResponseDto;
import com.example.DonorConnectFin.driveentity.Drive;
import com.example.DonorConnectFin.driveenums.DriveStatus;
import com.example.DonorConnectFin.driveservice.DriveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AppointmentServiceImpl  implements AppointmentService {
    @Autowired
    private  AppointmentRepository appointmentRepository;
    @Autowired
    private DriveService driveService;

    @Override
    public AppointmentResponseDTO bookAppointment(AppointmentRequestDTO requestDTO) {

        if(requestDTO.getDateTime().isBefore(LocalDateTime.now())){
            throw new RuntimeException("Appointment date must be in the future");
        }

        LocalDateTime startOfDay=requestDTO.getDateTime().toLocalDate().atStartOfDay();
        LocalDateTime endOfDay=requestDTO.getDateTime().toLocalDate().atTime(23,59,59);
        boolean alreadyBooked= appointmentRepository.existsByDonorIdAndDateTimeBetween(requestDTO.getDonorId(),startOfDay,endOfDay);
        if(alreadyBooked){
            throw new RuntimeException("Donor already has an appointment on this day");
        }
        Drive drive=null;
        if(requestDTO.getDriveId()!=null){
            drive=driveService.getDriveEntityById(requestDTO.getDriveId());

            if(drive.getStatus()!= DriveStatus.SCHEDULED &&
               drive.getStatus()!=DriveStatus.ACTIVE){
                throw new RuntimeException("Drive is not accepting appointments");

            }

            int currentCount=appointmentRepository.countByDrive_DriveId(requestDTO.getDriveId());
            if(currentCount>= drive.getCapacity()){
                throw new RuntimeException("Drive is full");
            }

        }

        DonationAppointment appointment=new DonationAppointment();
        appointment.setDonorId(requestDTO.getDonorId());
        appointment.setDateTime(requestDTO.getDateTime());
        appointment.setCenterId(requestDTO.getCenterId());
        appointment.setDrive(drive);
        appointment.setType(requestDTO.getType());
        appointment.setStatus(AppointmentStatus.BOOKED);

        DonationAppointment saved= appointmentRepository.save(appointment);
        return mapToResponseDTO(saved);

    }

    private AppointmentResponseDTO mapToResponseDTO(DonationAppointment appointment) {
        AppointmentResponseDTO response=new AppointmentResponseDTO();
        response.setAppointmentId(appointment.getAppointmentId());
        response.setDonorId(appointment.getDonorId());
        response.setDateTime(appointment.getDateTime());
        response.setCenterId(appointment.getCenterId());
        response.setStatus(appointment.getStatus());
        response.setType(appointment.getType());
        if(appointment.getDrive()!=null){
            response.setDriveId(appointment.getDrive().getDriveId());
            response.setDriveName(appointment.getDrive().getName());
            response.setDriveLocation(appointment.getDrive().getLocation());

        }
        return response;
    }

    @Override
    public AppointmentResponseDTO getAppointmentById(Long appointmentId) {
        DonationAppointment appointment=appointmentRepository.findById(appointmentId).
                orElseThrow(() -> new RuntimeException("Appointment not found with ID:" + appointmentId) );
        return mapToResponseDTO(appointment);
    }

    @Override
    public List<AppointmentResponseDTO> getAllAppointments() {
        return appointmentRepository.findAll()
                                     .stream()
                                      .map(this::mapToResponseDTO).collect(Collectors.toList());
    }

    @Override
    public List<AppointmentResponseDTO> getAppointmentsByDonorId(Long donorId) {
        return appointmentRepository.findByDonorId(donorId).
                stream().map(this::mapToResponseDTO).collect(Collectors.toList());
    }


    @Override
    public List<AppointmentResponseDTO> getTodaysAppointments() {
        LocalDateTime startOfDay= LocalDate.now().atStartOfDay();
        LocalDateTime endOfDay=LocalDate.now().atTime(23,59,59);
        return appointmentRepository.findByDateTimeBetween(startOfDay,endOfDay)
                .stream().map(this::mapToResponseDTO).collect(Collectors.toList());
    }

    @Override
    public AppointmentResponseDTO updateAppointment(Long appointmentId, AppointmentRequestDTO requestDTO) {
        DonationAppointment appointment=appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new RuntimeException("AppointmentId is incorrect"));
        appointment.setCenterId(requestDTO.getCenterId());
        appointment.setDateTime(requestDTO.getDateTime());
        appointment.setType(requestDTO.getType());
        appointment.setDonorId(requestDTO.getDonorId());
        if(requestDTO.getDriveId()!=null){
            Drive drive=driveService.getDriveEntityById(requestDTO.getDriveId());
            appointment.setDrive(drive);
        }


        DonationAppointment updated= appointmentRepository.save(appointment);
        return mapToResponseDTO(updated);
    }

    @Override
    public AppointmentResponseDTO checkIn(Long appointmentId) {
        DonationAppointment appointment=appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new RuntimeException("AppointmentId not exist "));

        if(appointment.getStatus() != AppointmentStatus.BOOKED){
            throw new RuntimeException("Only BOOKED Appointments can be Checked In");
        }
        appointment.setStatus(AppointmentStatus.CHECKED_IN);
        DonationAppointment updated=appointmentRepository.save(appointment);
        return mapToResponseDTO(updated);
    }

    @Override
    public AppointmentResponseDTO cancelAppointment(Long appointmentId) {
        DonationAppointment appointment=appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new RuntimeException("AppointmentId not exist "));

        if(appointment.getStatus() != AppointmentStatus.BOOKED){
            throw new RuntimeException("Only BOOKED Appointments can be Cancelled");
        }
        appointment.setStatus(AppointmentStatus.CANCELLED);
        DonationAppointment updated=appointmentRepository.save(appointment);
        return mapToResponseDTO(updated);


    }


    @Override
    public AppointmentResponseDTO markNoShow(Long appointmentId) {
        DonationAppointment appointment=appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new RuntimeException("AppointmentId not exist "));

        if(appointment.getStatus() != AppointmentStatus.BOOKED){
            throw new RuntimeException("Only BOOKED Appointments can be marked as No Show");
        }
        appointment.setStatus(AppointmentStatus.NO_SHOW);
        DonationAppointment updated=appointmentRepository.save(appointment);
        return mapToResponseDTO(updated);
    }

    @Override
    public AppointmentResponseDTO markCompleted(Long appointmentId) {
        DonationAppointment appointment=appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new RuntimeException("AppointmentId not exist "));

        if(appointment.getStatus() != AppointmentStatus.CHECKED_IN){
            throw new RuntimeException("Only CHECKED_IN Appointments can be marked as Completed");
        }
        appointment.setStatus(AppointmentStatus.COMPLETED);
        DonationAppointment updated=appointmentRepository.save(appointment);
        return mapToResponseDTO(updated);
    }
}
