package com.example.DonorConnectFin.appointmentdto;


import com.example.DonorConnectFin.appointmentenums.AppointmentStatus;
import com.example.DonorConnectFin.appointmentenums.AppointmentType;
import com.example.DonorConnectFin.drivedto.DriveResponseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentResponseDTO {
    private Long appointmentId;
    private Long donorId;
    private LocalDateTime dateTime;
    private Long centerId;

    private AppointmentStatus status;
    private AppointmentType type;

    private Long driveId;
    private String driveName;
    private String driveLocation;


}
