package com.example.DonorConnectFin.driveservice.impl;

import com.example.DonorConnectFin.driveservice.DriveService;
import com.example.DonorConnectFin.drivedto.DriveRequestDto;
import com.example.DonorConnectFin.drivedto.DriveResponseDto;
import com.example.DonorConnectFin.drivedto.DriveStatusDto;
import com.example.DonorConnectFin.driveentity.Drive;
import com.example.DonorConnectFin.driveenums.DriveStatus;
import com.example.DonorConnectFin.driverepository.DriveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DriveServiceImpl implements DriveService {

    @Autowired
    private DriveRepository driveRepository;

    // Convert Entity → ResponseDto
    private DriveResponseDto toResponseDto(Drive drive) {
        DriveResponseDto dto = new DriveResponseDto();
        dto.setDriveId(drive.getDriveId());
        dto.setName(drive.getName());
        dto.setLocation(drive.getLocation());
        dto.setScheduledDate(drive.getScheduledDate());
        dto.setCapacity(drive.getCapacity());
        dto.setOrganizer(drive.getOrganizer());
        dto.setStatus(drive.getStatus());
        return dto;
    }


    // Convert RequestDto → Entity
    private Drive toEntity(DriveRequestDto dto) {
        Drive drive = new Drive();
        drive.setName(dto.getName());
        drive.setLocation(dto.getLocation());
        drive.setScheduledDate(dto.getScheduledDate());
        drive.setCapacity(dto.getCapacity());
        drive.setOrganizer(dto.getOrganizer());
        drive.setStatus(DriveStatus.SCHEDULED);
        return drive;
    }

    // Create drive
    public DriveResponseDto createDrive(DriveRequestDto requestDto) {
        Drive drive = toEntity(requestDto);
        Drive saved = driveRepository.save(drive);
        return toResponseDto(saved);
    }
    // Get all drives
    public List<DriveResponseDto> getAllDrives() {
        List<Drive> drives = driveRepository.findAll();
        List<DriveResponseDto> result = new ArrayList<>();
        for (Drive drive : drives) {
            result.add(toResponseDto(drive));
        }
        return result;
    }

    // Get drive by ID
    public DriveResponseDto getDriveById(Long driveId) {
        Optional<Drive> optional = driveRepository.findById(driveId);
        if (optional.isPresent()) {
            return toResponseDto(optional.get());
        }
        throw new RuntimeException("Drive not found with id: " + driveId);
    }


    // Get upcoming drives
    public List<DriveResponseDto> getUpcomingDrives() {
        List<Drive> drives = driveRepository.findByScheduledDateAfter(LocalDate.now());
        List<DriveResponseDto> result = new ArrayList<>();
        for (Drive drive : drives) {
            result.add(toResponseDto(drive));
        }
        return result;
    }
    public DriveResponseDto updateDrive(Long driveId, DriveRequestDto requestDto) {
        Optional<Drive> optional = driveRepository.findById(driveId);
        if (!optional.isPresent()) {
            throw new RuntimeException("Drive not found with id: " + driveId);
        }
        Drive existing = new Drive();
        existing.setName(requestDto.getName());
        existing.setLocation(requestDto.getLocation());
        existing.setScheduledDate(requestDto.getScheduledDate());
        existing.setCapacity(requestDto.getCapacity());
        existing.setOrganizer(requestDto.getOrganizer());
        Drive updated = driveRepository.save(existing);
        return toResponseDto(updated);
    }
    public DriveResponseDto updateDriveStatus(Long driveId, DriveStatusDto statusDto) {
        Optional<Drive> optional = driveRepository.findById(driveId);
        if (!optional.isPresent()) {
            throw new RuntimeException("Drive not found with id: " + driveId);
        }
        Drive existing = optional.get();
        existing.setStatus(statusDto.getStatus());
        Drive updated = driveRepository.save(existing);
        return toResponseDto(updated);
    }

    @Override
    public Drive getDriveEntityById(Long driveId) {
        return driveRepository.findById(driveId).orElseThrow(()-> new RuntimeException("Drive not found"));
    }
}