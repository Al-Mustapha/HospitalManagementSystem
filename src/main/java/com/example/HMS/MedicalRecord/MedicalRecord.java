package com.example.HMS.MedicalRecord;

import com.example.HMS.Users.Doctor.Doctor;
import com.example.HMS.Users.Patient.Patient;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.DatabindException;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "medicalrecord")
@Data
public class MedicalRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long medicalRecordId;
    @Column(nullable = false)
    private LocalDate dateOfVisit;
    private String historyOfPresentIllness;
    private String physicalExaminationFindings;
    private String diagnosticTestResults;
    private String diagnosis;
    @Column(nullable = false)
    private String medicationsPrescribed;

    @JsonIgnore
    @ManyToOne(
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL
    )
    @JoinColumn(
            name = "patient_id",
            nullable = false
    )
    private Patient patient;

//    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    @JoinColumn(
//     name = "doctor_id"
//    )
//    private Doctor doctor;

}
