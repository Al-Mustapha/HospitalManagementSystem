package com.example.HMS.MedicalRecord;

import com.example.HMS.Users.Patient.Patient;
import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jdk.jfr.DataAmount;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
public class MedicalRecordModel {

    private Long medicalRecordId;
    private LocalDate dateOfVisit;
    private String historyOfPresentIllness;
    private String physicalExaminationFindings;
    private String diagnosticTestResults;
    private String diagnosis;
    private String medicationsPrescribed;
}
