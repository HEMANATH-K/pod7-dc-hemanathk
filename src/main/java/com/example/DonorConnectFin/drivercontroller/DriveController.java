package com.example.DonorConnectFin.drivercontroller;

import com.example.DonorConnectFin.drivedto.DriveRequestDto;
import com.example.DonorConnectFin.drivedto.DriveResponseDto;
import com.example.DonorConnectFin.drivedto.DriveStatusDto;
import com.example.DonorConnectFin.driveservice.DriveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/drives")
public class DriveController {

    @Autowired
    private DriveService driveService;

    // POST /drives
    @PostMapping("/post")
    public ResponseEntity<DriveResponseDto> createDrive(@RequestBody DriveRequestDto requestDto) {
        return ResponseEntity.ok(driveService.createDrive(requestDto));
    }

    // GET /drives
    @GetMapping("/alldrives")
    public ResponseEntity<List<DriveResponseDto>> getAllDrives() {
        return ResponseEntity.ok(driveService.getAllDrives());
    }

    // GET /drives/upcoming  ← must be before /{driveId}
    @GetMapping("/upcoming")
    public ResponseEntity<List<DriveResponseDto>> getUpcomingDrives() {
        return ResponseEntity.ok(driveService.getUpcomingDrives());
    }

    // GET /drives/{driveId}
    @GetMapping("/{driveId}")
    public ResponseEntity<DriveResponseDto> getDriveById(@PathVariable Long driveId) {
        return ResponseEntity.ok(driveService.getDriveById(driveId));
    }

    // PUT /drives/{driveId}
    @PutMapping("/{driveId}")
    public ResponseEntity<DriveResponseDto> updateDrive(@PathVariable Long driveId,
                                                         @RequestBody DriveRequestDto requestDto) {
        return ResponseEntity.ok(driveService.updateDrive(driveId, requestDto));
    }

    // PATCH /drives/{driveId}/status
    @PatchMapping("/{driveId}/status")
    public ResponseEntity<DriveResponseDto> updateStatus(@PathVariable Long driveId,
                                                          @RequestBody DriveStatusDto statusDto) {
        return ResponseEntity.ok(driveService.updateDriveStatus(driveId, statusDto));
    }

    // GET /drives/{driveId}/appointments
    @GetMapping("/{driveId}/appointments")
    public ResponseEntity<String> getAppointments(@PathVariable Long driveId) {
        return ResponseEntity.ok("Appointments for driveId: " + driveId);
    }
}
