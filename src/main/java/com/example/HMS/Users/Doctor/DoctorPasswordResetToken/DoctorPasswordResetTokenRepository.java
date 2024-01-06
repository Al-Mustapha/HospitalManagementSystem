package com.example.HMS.Users.Doctor.DoctorPasswordResetToken;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorPasswordResetTokenRepository extends JpaRepository<DoctorPasswordResetToken, Long> {
    DoctorPasswordResetToken findByToken(String token);
}
