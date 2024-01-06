package com.example.HMS.Users.Laboratorist.LaboratoristAccountConfirmation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LaboratoristAccountConfirmationRepository extends JpaRepository<LaboratoristAccountConfirmation, Long> {
    LaboratoristAccountConfirmation findByToken(String token);
}
