package com.example.HMS.Users.Doctor.DoctorPasswordResetToken;

import com.example.HMS.Users.Doctor.Doctor;
import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;

@Entity
@Data
@RequiredArgsConstructor
public class DoctorPasswordResetToken {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long passwordResetTokenId;
    private String token;
    private Date expirationTime;

    @ManyToOne(
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    @JoinColumn(
            name = "doctor_id",
            nullable = false
    )
    private Doctor doctor;

    public DoctorPasswordResetToken(Doctor doctor, String token){
        this.doctor = doctor;
        this.token = token;
        this.expirationTime = calculateExpirationTime();
    }

    private Date calculateExpirationTime() {
        Calendar calendar =
                Calendar.getInstance();
        calendar.setTimeInMillis(new Date().getTime());
        calendar.add(calendar.MINUTE, 10);
        return new Date(calendar.getTime().getTime());
    }


}
