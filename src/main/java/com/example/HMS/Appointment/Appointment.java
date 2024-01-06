package com.example.HMS.Appointment;

import com.example.HMS.PayStackForPayment.AppointmentPayment.AppointmentPayment;
import com.example.HMS.Security.AppointmentPaymentStatus;
import com.example.HMS.Users.Patient.Patient;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "appointment")
@Data
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long appointmentId;
    @Column(nullable = false)
    private String appointmentDate;
    @Column(nullable = false)
    private String appointmentTime;
    @Column(nullable = false)
    private String appointmentStatus;

    @Enumerated(EnumType.STRING)
    private AppointmentPaymentStatus paymentStatus;

    @ManyToOne(
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
    )
    @JoinColumn(
            name = "patient_id"
    )
    private Patient patient;

}
