package com.example.HMS.Prescription;

import jakarta.persistence.Column;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class PrescriptionModel {
    private String medicationName;
    private String dosage;
    private Long patientId;
}
