package com.example.HMS.Pharmacy;

import com.example.HMS.Users.Patient.Patient;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class Pharmacy {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long pharmacyId;
    @Column(nullable = false)
    private String medicationName;
    @Column(nullable = false)
    private Long quantityAvailable;
    @Column(nullable = false)
    private LocalDate expiryDate;
    @Column(nullable = false)
    private String supplier;
    @Column(nullable = false)
    private Long price;
    private String medicationImage;

}
