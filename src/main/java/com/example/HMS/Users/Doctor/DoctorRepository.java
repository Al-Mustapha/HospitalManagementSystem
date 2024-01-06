package com.example.HMS.Users.Doctor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import javax.print.Doc;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {

    Doctor findUserByUsername(String username);

    Doctor findByEmail(String email);
}
