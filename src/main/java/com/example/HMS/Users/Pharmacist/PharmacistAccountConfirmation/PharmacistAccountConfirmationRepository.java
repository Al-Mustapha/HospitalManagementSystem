package com.example.HMS.Users.Pharmacist.PharmacistAccountConfirmation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PharmacistAccountConfirmationRepository extends JpaRepository<PharmacistAccountConfirmation, Long> {
    PharmacistAccountConfirmation findByToken(String token);
}
