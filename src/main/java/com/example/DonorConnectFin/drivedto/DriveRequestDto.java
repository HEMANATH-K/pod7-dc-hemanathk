package com.example.DonorConnectFin.drivedto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class DriveRequestDto {

    private String name;
    private String location;
    private LocalDate scheduledDate;
    private Integer capacity;
    private String organizer;
}
