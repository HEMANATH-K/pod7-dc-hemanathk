package com.example.DonorConnectFin.driveentity;
import com.example.DonorConnectFin.appointmententity.DonationAppointment;
import com.example.DonorConnectFin.driveenums.DriveStatus;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.util.List;
@Entity
@Table(name = "drive")
@Data
public class Drive {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="drive_id")
    private Long driveId;
    private String name;
    private String location;
    @Column(name="scheduled_date")
    private LocalDate scheduledDate;
    private Integer capacity;
    private String organizer;
    @Column(name="status")
    @Enumerated(EnumType.STRING)
    private DriveStatus status;
    @OneToMany(mappedBy="drive", cascade=CascadeType.ALL)
    private List<DonationAppointment> appointments;

}
