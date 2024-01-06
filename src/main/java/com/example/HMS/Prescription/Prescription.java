package com.example.HMS.Prescription;

import com.example.HMS.Users.Doctor.Doctor;
import com.example.HMS.Users.Patient.Patient;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
public class Prescription {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long prescriptionId;
    @Column(nullable = false)
    private LocalDate prescriptionDate = LocalDate.now();
    @Column(nullable = false)
    private LocalDateTime prescriptionTime = LocalDateTime.now();
    @Column(nullable = false)
    private String medicationName;

    @Column(nullable = false)
    private String dosage;

    @ManyToOne(
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL
    )
    @JoinColumn(
            name = "patient_id",
            nullable = false
    )
    private Patient patient;

    @ManyToOne(
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL
    )
    @JoinColumn(
            name = "doctor_id",
            nullable = false
    )
    private Doctor doctor;

}
