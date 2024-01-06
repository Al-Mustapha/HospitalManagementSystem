package com.example.HMS.Users.Nurse.NursePasswordResetToken;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NursePasswordResetTokenRepository extends JpaRepository<NursePasswordResetToken, Long> {
    NursePasswordResetToken findByToken(String token);
}
