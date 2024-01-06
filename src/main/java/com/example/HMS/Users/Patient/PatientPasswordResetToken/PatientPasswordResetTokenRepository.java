package com.example.HMS.Users.Patient.PatientPasswordResetToken;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientPasswordResetTokenRepository extends JpaRepository<PatientPasswordResetToken, Long> {
    PatientPasswordResetToken findByToken(String token);

//    @Query(
//            name = "my_query",
//            nativeQuery = true,
//            value = "SELECT password_reset_id, token, expiration_time, patient_id " +
//                    "FROM patient_password_reset_token WHERE token=:token "
//    )
//    PatientPasswordResetToken findByTokenWithoutPatient(@Param("token") String token);
}
