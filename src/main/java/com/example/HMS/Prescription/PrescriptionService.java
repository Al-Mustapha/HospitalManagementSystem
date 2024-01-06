package com.example.HMS.Prescription;

import com.example.HMS.Authentication.JwtUtils;
import com.example.HMS.Users.Doctor.Doctor;
import com.example.HMS.Users.Doctor.DoctorRepository;
import com.example.HMS.Users.Patient.Patient;
import com.example.HMS.Users.Patient.PatientRepository;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@Slf4j
public class PrescriptionService {

    @Autowired
    public PatientRepository patientRepository;

    @Autowired
    public DoctorRepository doctorRepository;
    @Autowired
    public PrescriptionRepository prescriptionRepository;

    @Autowired
    public JwtUtils jwtUtils;
    public String addPrescription(PrescriptionModel prescriptionModel, HttpServletRequest request) {
        Prescription prescription =
                new Prescription();

        String authorization = request.getHeader("authorization");
        String token = authorization.substring(7);
        Claims extractedClaims = jwtUtils.extractAllClaims(token);

        Doctor doctor =
                doctorRepository.findUserByUsername(extractedClaims.getSubject());

        Optional<Patient> patient =
                Optional.ofNullable(patientRepository.findById(prescriptionModel.getPatientId()).get());

        if (patient.isPresent()){
            prescription.setDosage(prescriptionModel.getDosage());
            prescription.setMedicationName(prescriptionModel.getMedicationName());
            prescription.setDoctor(doctor);
            prescription.setPatient(patient.get());
            prescriptionRepository.save(prescription);
        }

        return "Prescription sent";

    }

    public Prescription getPatientPrescription(Long id) {
        return prescriptionRepository.findById(id).get();
    }

    public String deletePatientPrescription(Long id) {
        prescriptionRepository.deleteById(id);
        return "Patient prescription successfully deleted";
    }

    public String updatePatientPrescription(Long id, PrescriptionModel prescriptionModel) {
        Prescription prescription =
                prescriptionRepository.findById(id).get();
        prescription.setPrescriptionTime(LocalDateTime.now());
        prescription.setPrescriptionDate(LocalDate.now());
        prescription.setMedicationName(prescriptionModel.getMedicationName());
        prescription.setDosage(prescriptionModel.getDosage());
        prescriptionRepository.save(prescription);
        return "Prescription updated";
    }

    public String deleteAllPrescriptions() {
        prescriptionRepository.deleteAll();
        return "All prescriptions have been deleted";
    }
}
