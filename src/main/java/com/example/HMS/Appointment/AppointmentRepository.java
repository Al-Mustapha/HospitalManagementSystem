package com.example.HMS.Appointment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    @Query(
            name = "my_query",
            nativeQuery = true,
            value = "SELECT * FROM appointment INNER JOIN appointment_payment ON " +
                    "appointment.appointment_id = appointment_payment.user_appointment_id " +
                    "WHERE patient_id=:patientId"
    )
    List<Appointment> findAppointmentsByPatientId(@Param("patientId") Long patientId);
}
