package com.example.HMS.MedicalRecord;

import com.example.HMS.Authentication.JwtUtils;
import com.example.HMS.Users.Patient.Patient;
import com.example.HMS.Users.Patient.PatientRepository;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MedicalRecordService {

    @Autowired
    public MedicalRecordRepository medicalRecordRepository;

    @Autowired
    public PatientRepository patientRepository;

    @Autowired
    public JwtUtils jwtUtils;

    public String addMedicalRecord(MedicalRecordModel medicalRecordModel, HttpServletRequest request) {
        String authorization = request.getHeader("authorization");
        String token = authorization.substring(7);

        Claims claims = jwtUtils.extractAllClaims(token);

        Patient patient =
              patientRepository.findUserByUsername(claims.getSubject());
        MedicalRecord medicalRecord =
                new MedicalRecord();
        BeanUtils.copyProperties(medicalRecordModel, medicalRecord);
        medicalRecord.setPatient(patient);

        medicalRecordRepository.save(medicalRecord);
        return "Medical Record Added";
    }

    public List<MedicalRecord> getPatientMedicalRecords() {
        return medicalRecordRepository.findAll();
    }

    public ResponseEntity<MedicalRecord> getPatientMedicalRecord(Long id) {
        Optional<MedicalRecord> medicalRecord =
                Optional.ofNullable(medicalRecordRepository.findMedicalRecordByPatientId(id));
        if (medicalRecord.isPresent()){
            return ResponseEntity.ok(medicalRecord.get());
        }
        else {
            throw new UsernameNotFoundException("Medical records for such user does not exist");
        }
    }

    public String editPatientMedicalRecord(Long id, MedicalRecordModel medicalRecordModel) {
        Optional<MedicalRecord> medicalRecord =
                Optional.ofNullable(medicalRecordRepository.findById(id).get());
        if (medicalRecord.isPresent()){
            medicalRecord.get().setMedicationsPrescribed(medicalRecordModel.getMedicationsPrescribed());
            medicalRecord.get().setDiagnosis(medicalRecordModel.getDiagnosis());
            medicalRecord.get().setDateOfVisit(medicalRecordModel.getDateOfVisit());
            medicalRecord.get().setDiagnosticTestResults(medicalRecordModel.getDiagnosticTestResults());
            medicalRecord.get().setHistoryOfPresentIllness(medicalRecordModel.getHistoryOfPresentIllness());
            medicalRecord.get().setPhysicalExaminationFindings(medicalRecordModel.getPhysicalExaminationFindings());
            medicalRecordRepository.save(medicalRecord.get());
            return "Medical Record Updated Successfully";
        }
        else {
            return "No such medical record exists";
        }
    }
}
