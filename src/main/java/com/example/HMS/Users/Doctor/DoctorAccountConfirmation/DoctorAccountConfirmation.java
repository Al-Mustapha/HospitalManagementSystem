package com.example.HMS.Users.Doctor.DoctorAccountConfirmation;

import com.example.HMS.Users.Doctor.Doctor;
import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;

@Entity
@RequiredArgsConstructor
@Data
public class DoctorAccountConfirmation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long accountConfirmationId;
    @Column(nullable = false, unique = true)
    private String token;
    @Column(nullable = false)
    private Date expirationTime;

    @OneToOne(
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    @JoinColumn(
            name = "doctor_id",
            nullable = false
    )
    private Doctor doctor;

    public DoctorAccountConfirmation(Doctor doctor, String token){
        this.doctor = doctor;
        this.token = token;
        expirationTime = calculateExpirationTime();
    }

    private Date calculateExpirationTime() {
        Calendar calendar =
                Calendar.getInstance();
        calendar.setTimeInMillis(new Date().getTime());
        calendar.add(calendar.MINUTE, 10);
        return new Date(calendar.getTime().getTime());
    }


}
