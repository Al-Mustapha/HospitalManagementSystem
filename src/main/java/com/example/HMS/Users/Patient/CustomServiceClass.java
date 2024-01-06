package com.example.HMS.Users.Patient;

import com.example.HMS.Users.Doctor.DoctorRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CustomServiceClass implements UserDetailsService {

    @Autowired
    public PatientRepository patientRepository;

    @Autowired
    public DoctorRepository doctorRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserDetails userDetails = null;

        Patient patient =
                patientRepository.findUserByUsername(username);

        if (patient != null && patient.isEnabled()) {
            userDetails = patient;
        }
        UserDetails doctor =
                doctorRepository.findUserByUsername(username);
        if (doctor != null && doctor.isEnabled()) {
            userDetails = doctor;
        }
        return userDetails;
    }
}
