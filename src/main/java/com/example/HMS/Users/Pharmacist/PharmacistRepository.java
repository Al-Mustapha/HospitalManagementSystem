package com.example.HMS.Users.Pharmacist;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PharmacistRepository extends JpaRepository<Pharmacist, Long> {
    Pharmacist findByEmail(String email);
}
