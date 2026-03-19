package com.example.DonorConnectFin.driveservice;
import com.example.DonorConnectFin.drivedto.DriveRequestDto;
import com.example.DonorConnectFin.drivedto.DriveResponseDto;
import com.example.DonorConnectFin.drivedto.DriveStatusDto;
import com.example.DonorConnectFin.driveentity.Drive;

import java.util.List;

public interface DriveService {
    // Convert Entity → ResponseDto
//    private DriveResponseDto toResponseDto(Drive drive) {
//        DriveResponseDto dto = new DriveResponseDto();
//        dto.setDriveId(drive.getDriveId());
//        dto.setName(drive.getName());
//        dto.setLocation(drive.getLocation());
//        dto.setScheduledDate(drive.getScheduledDate());
//        dto.setCapacity(drive.getCapacity());
//        dto.setOrganizer(drive.getOrganizer());
//        dto.setStatus(drive.getStatus());
//        return dto;
//    }

    // Convert RequestDto → Entity
//    private Drive toEntity(DriveRequestDto dto) {
//        Drive drive = new Drive();
//        drive.setName(dto.getName());
//        drive.setLocation(dto.getLocation());
//        drive.setScheduledDate(dto.getScheduledDate());
//        drive.setCapacity(dto.getCapacity());
//        drive.setOrganizer(dto.getOrganizer());
//        drive.setStatus(DriveStatus.SCHEDULED);
//        return drive;
//    }


    public DriveResponseDto createDrive(DriveRequestDto requestDto) ;

    // Get all drives
    public List<DriveResponseDto> getAllDrives() ;

    // Get drive by ID
    public DriveResponseDto getDriveById(Long driveId) ;

    // Get upcoming drives
    public List<DriveResponseDto> getUpcomingDrives() ;


    // Update drive
    public DriveResponseDto updateDrive(Long driveId, DriveRequestDto requestDto) ;

    // Update status only
    public DriveResponseDto updateDriveStatus(Long driveId, DriveStatusDto statusDto) ;
    Drive getDriveEntityById(Long driveId);
}
