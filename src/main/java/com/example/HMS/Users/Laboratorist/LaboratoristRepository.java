package com.example.HMS.Users.Laboratorist;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LaboratoristRepository extends JpaRepository<Laboratorist, Long> {
    Laboratorist findByEmail(String email);
}
