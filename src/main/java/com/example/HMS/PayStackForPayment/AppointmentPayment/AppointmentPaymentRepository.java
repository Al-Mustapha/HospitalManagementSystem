package com.example.HMS.PayStackForPayment.AppointmentPayment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentPaymentRepository extends JpaRepository<AppointmentPayment, Long> {
    AppointmentPayment findByReference(String reference);
}
