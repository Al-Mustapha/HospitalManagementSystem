package com.example.HMS.Users.Patient.PatientAccountConfirmation;

import com.example.HMS.Users.Patient.Patient;
import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Calendar;
import java.util.Date;

@Entity
@RequiredArgsConstructor
@Data
public class PatientAccountConfirmation {

    private static final int EXPIRATION_TIME = 10;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long patientAccountConfirmationId;
    private String token;
    private Date expirationTime;

    @OneToOne(
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    @JoinColumn(
            name = "patient_id",
            nullable = false
    )
    private Patient patient;

    public PatientAccountConfirmation(Patient patient, String token){
        this.patient = patient;
        this.token = token;
        expirationTime = calculateExpirationTime(EXPIRATION_TIME);
    }

    private Date calculateExpirationTime(int expirationTime) {
        Calendar calendar =
                Calendar.getInstance();
        calendar.setTimeInMillis(new Date().getTime());
        calendar.add(Calendar.MINUTE, expirationTime);
        return new Date(calendar.getTime().getTime());

    }


}
