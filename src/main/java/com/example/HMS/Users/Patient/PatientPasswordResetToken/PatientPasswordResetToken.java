package com.example.HMS.Users.Patient.PatientPasswordResetToken;

import com.example.HMS.Users.Patient.Patient;
import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;

@Entity
@Data
@RequiredArgsConstructor
public class PatientPasswordResetToken {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long passwordResetId;
    @Column(nullable = false, unique = true)
    private String token;
    @Column(nullable = false)
    private Date expirationTime;

    @ManyToOne(
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    @JoinColumn(
            name = "patient_id",
            nullable = false
    )
    private Patient patient;

    public PatientPasswordResetToken(Patient patient, String token){
        this.patient = patient;
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

    public PatientPasswordResetToken(String token){
        this.token = token;
        this.expirationTime = calculateExpirationTime();
    }

}
