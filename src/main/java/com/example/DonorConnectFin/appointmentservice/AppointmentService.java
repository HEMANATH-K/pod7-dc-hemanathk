package com.example.DonorConnectFin.appointmentservice;

import com.example.DonorConnectFin.appointmentdto.AppointmentRequestDTO;
import com.example.DonorConnectFin.appointmentdto.AppointmentResponseDTO;

import java.util.List;

public interface AppointmentService {

    AppointmentResponseDTO bookAppointment(AppointmentRequestDTO requestDTO);

    AppointmentResponseDTO getAppointmentById(Long appointmentId);

    List<AppointmentResponseDTO> getAllAppointments();

    List<AppointmentResponseDTO> getAppointmentsByDonorId(Long donorId);

    List<AppointmentResponseDTO> getTodaysAppointments();

    AppointmentResponseDTO updateAppointment(Long appointmentId, AppointmentRequestDTO requestDTO);

    AppointmentResponseDTO checkIn(Long appointmentId);

    AppointmentResponseDTO cancelAppointment(Long appointmentId);

    AppointmentResponseDTO markNoShow(Long appointmentId);

    AppointmentResponseDTO markCompleted(Long appointmentId);



}
