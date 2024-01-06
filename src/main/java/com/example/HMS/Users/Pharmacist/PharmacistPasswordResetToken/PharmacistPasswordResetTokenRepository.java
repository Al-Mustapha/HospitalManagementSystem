package com.example.HMS.Users.Pharmacist.PharmacistPasswordResetToken;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PharmacistPasswordResetTokenRepository extends JpaRepository<PharmacistPasswordResetToken, Long> {
    PharmacistPasswordResetToken findByToken(String token);
}
