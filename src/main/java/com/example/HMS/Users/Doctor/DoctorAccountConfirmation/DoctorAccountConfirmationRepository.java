package com.example.HMS.Users.Doctor.DoctorAccountConfirmation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorAccountConfirmationRepository extends JpaRepository<DoctorAccountConfirmation, Long> {
    DoctorAccountConfirmation findByToken(String token);
}
