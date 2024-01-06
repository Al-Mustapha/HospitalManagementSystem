package com.example.HMS.MedicalRecord;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicalRecordRepository extends JpaRepository<MedicalRecord, Long> {
    @Query(
            nativeQuery = true,
            name = "my_query",
            value = "SELECT * FROM medicalrecord s WHERE s.patient_id=:patientId"
    )
    MedicalRecord findMedicalRecordByPatientId(@Param("patientId") Long id);

}
