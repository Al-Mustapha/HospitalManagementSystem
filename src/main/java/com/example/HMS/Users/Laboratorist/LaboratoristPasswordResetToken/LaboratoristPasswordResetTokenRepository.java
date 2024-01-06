package com.example.HMS.Users.Laboratorist.LaboratoristPasswordResetToken;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LaboratoristPasswordResetTokenRepository extends JpaRepository<LaboratoristPasswordResetToken, Long> {
    LaboratoristPasswordResetToken findByToken(String token);
}
