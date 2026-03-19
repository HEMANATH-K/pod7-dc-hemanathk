package com.example.DonorConnectFin.appointmententity;


import com.example.DonorConnectFin.appointmentenums.AppointmentStatus;
import com.example.DonorConnectFin.appointmentenums.AppointmentType;
import com.example.DonorConnectFin.driveentity.Drive;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "donation_appointment")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DonationAppointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "appointment_id")
    private Long appointmentId;

    @Column(name = "donor_id")
    private Long donorId;

    @Column(name = "datetime")
    private LocalDateTime dateTime;

    @Column(name = "center_id")
    private Long centerId;

//    @Column(name = "drive_id")
//    private Long driveId;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private AppointmentStatus status;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private AppointmentType type;

    @ManyToOne
    @JoinColumn(name="drive_id")
    private Drive drive;


}
