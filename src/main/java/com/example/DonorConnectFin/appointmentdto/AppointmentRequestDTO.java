package com.example.DonorConnectFin.appointmentdto;


import com.example.DonorConnectFin.appointmentenums.AppointmentType;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentRequestDTO {

    @NotNull(message="Donor ID is required")
    private long donorId;

    @NotNull(message = "Date and Time is required")
    @Future(message = "Appointment must be in the future")
    private LocalDateTime dateTime;

    @NotNull(message = "Center ID is required")
    private Long centerId;

    private Long driveId;

    @NotNull(message = "Appointment type is required")

    private AppointmentType type;


}
