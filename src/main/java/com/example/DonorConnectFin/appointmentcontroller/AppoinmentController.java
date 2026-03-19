package com.example.DonorConnectFin.appointmentcontroller;

import com.example.DonorConnectFin.appointmentdto.AppointmentRequestDTO;
import com.example.DonorConnectFin.appointmentdto.AppointmentResponseDTO;
import com.example.DonorConnectFin.appointmentservice.AppointmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/appointments")
@RequiredArgsConstructor
public class AppoinmentController {
    @Autowired
    private AppointmentService appointmentService;

    @PostMapping
    public ResponseEntity<AppointmentResponseDTO> bookAppointment(@Valid @RequestBody AppointmentRequestDTO requestDTO){
        AppointmentResponseDTO response=appointmentService.bookAppointment(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<AppointmentResponseDTO>> getAllAppointments(){
        List<AppointmentResponseDTO> response=appointmentService.getAllAppointments();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{appointmentId}")
    public ResponseEntity<AppointmentResponseDTO> getAppointmentById(@PathVariable Long appointmentId){
        AppointmentResponseDTO response=appointmentService.getAppointmentById(appointmentId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/donor/{donorId}")
    public ResponseEntity<List<AppointmentResponseDTO>> getAppointmentByDonorId(@PathVariable Long donorId){
        List<AppointmentResponseDTO> response=appointmentService.getAppointmentsByDonorId(donorId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/today")
    public ResponseEntity <List<AppointmentResponseDTO>> getTodaysAppointments(){
        List<AppointmentResponseDTO> response=appointmentService.getTodaysAppointments();
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{appointmentId}")
    public ResponseEntity<AppointmentResponseDTO> updateAppointment(@PathVariable Long appointmentId, @Valid @RequestBody AppointmentRequestDTO requestDTO){
        AppointmentResponseDTO response=appointmentService.updateAppointment(appointmentId,requestDTO);
        return  ResponseEntity.ok(response);
    }

    @PatchMapping("/{appointmentId}/check-in")
    public ResponseEntity<AppointmentResponseDTO> checkIn(@PathVariable Long appointmentId){
        AppointmentResponseDTO response=appointmentService.checkIn(appointmentId);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{appointmentId}/cancel")
    public ResponseEntity<AppointmentResponseDTO> cancelAppointment(@PathVariable Long appointmentId){
        AppointmentResponseDTO response=appointmentService.cancelAppointment(appointmentId);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{appointmentId}/no-show")
    public ResponseEntity<AppointmentResponseDTO> markNoShow(@PathVariable Long appointmentId){
        AppointmentResponseDTO response=appointmentService.markNoShow(appointmentId);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{appointmentId}/complete")
    public ResponseEntity<AppointmentResponseDTO> markCompleted(@PathVariable Long appointmentId){
        AppointmentResponseDTO response=appointmentService.markCompleted(appointmentId);
        return ResponseEntity.ok(response);
    }

















}
