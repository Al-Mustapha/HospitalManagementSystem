package com.example.HMS.Users.Nurse.NurseAccountConfirmation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NurseAccountConfirmationRepository extends JpaRepository<NurseAccountConfirmation, Long> {
    NurseAccountConfirmation findByToken(String token);
}
