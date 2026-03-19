package com.example.DonorConnectFin.drivedto;

import com.example.DonorConnectFin.driveenums.DriveStatus;
import lombok.Data;
import java.time.LocalDate;

@Data
public class DriveResponseDto {

    private Long driveId;
    private String name;
    private String location;
    private LocalDate scheduledDate;
    private Integer capacity;
    private String organizer;
    private DriveStatus status;
}
