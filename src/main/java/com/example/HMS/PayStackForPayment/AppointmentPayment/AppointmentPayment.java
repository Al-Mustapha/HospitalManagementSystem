package com.example.HMS.PayStackForPayment.AppointmentPayment;

import com.example.HMS.Appointment.Appointment;
import com.example.HMS.Security.AppointmentPaymentStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class AppointmentPayment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private double amount;
    private String reference;
    private String email;
    private LocalDate paymentDate = LocalDate.now();
    private LocalDateTime  paymentTime = LocalDateTime.now();
    @Enumerated(EnumType.STRING)
    private AppointmentPaymentStatus status;

    @OneToOne(
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL
    )
    @JoinColumn(
            name = "user_appointment_id"
    )
    private Appointment appointments;

    public AppointmentPayment(Appointment appointment, AppointmentPayment appointmentPayment){
        this.appointments = appointment;
    }


    public AppointmentPayment() {

    }
}
