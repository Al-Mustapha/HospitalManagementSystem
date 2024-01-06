package com.example.HMS.Users.Patient;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
//    @Query(
//            name = "my_query",
//            nativeQuery = true,
//            value = "select * from patient p where p.username=:username"
//    )
    Patient findUserByUsername(String username);

    Patient findByEmail(String email);
//    @Query(
//            nativeQuery = true,
//            value = "SELECT patient_id, first_name, address " +
//                    "FROM patient WHERE patient_id=:id"
//    )
//    Patient getPatientById(@Param("id") Long id);
}
