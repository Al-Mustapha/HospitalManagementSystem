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
            value = "SELECT appointment_id, appointment_date, appointment_status, appointment_time, doctor_id, payment_status, patient_id," +
                    "id, amount, name, reference, email, status, appoint, payment_date, payment_time, user_appointment_id FROM appointment, appointment_payment\n" +
                    "WHERE patient_id=13"
//            value = "SELECT * FROM appointment a INNER JOIN appointment_payment ap ON a.appointment_id = ap.user_appointment_id\n" +
//            "WHERE a.patient_id=:patientId"
    )
    List<Appointment> findAppointmentsByPatientId(@Param("patientId") Long patientId);
}
