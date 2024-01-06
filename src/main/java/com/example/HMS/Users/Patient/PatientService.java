package com.example.HMS.Users.Patient;

import com.example.HMS.GmailSenderService;
import com.example.HMS.Users.Patient.PatientAccountConfirmation.PatientAccountConfirmation;
import com.example.HMS.Users.Patient.PatientAccountConfirmation.PatientAccountConfirmationRepository;
import com.example.HMS.Users.Patient.PatientPasswordResetToken.PatientPasswordResetModel;
import com.example.HMS.Users.Patient.PatientPasswordResetToken.PatientPasswordResetToken;
import com.example.HMS.Users.Patient.PatientPasswordResetToken.PatientPasswordResetTokenRepository;
import com.example.HMS.Security.UserRole;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import static com.example.HMS.Security.UserRole.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class PatientService {
    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private GmailSenderService gmailSenderService;

    @Autowired
    public PatientAccountConfirmationRepository patientAccountConfirmationRepository;

    @Autowired
    public PatientPasswordResetTokenRepository patientPasswordResetTokenRepository;;

    public ResponseEntity<String> createAccount(Patient patient) throws MessagingException {
        if (patient.getPassword().equals(patient.getConfirmPassword())){
            patient.setPassword(passwordEncoder.encode(patient.getPassword()));
            patient.setConfirmPassword(passwordEncoder.encode(patient.getConfirmPassword()));
            patient.setRole(UserRole.valueOf(PATIENT.name()));

            String token = UUID.randomUUID().toString();
            PatientAccountConfirmation patientAccountConfirmation
                    = new PatientAccountConfirmation(patient, token);

            patientAccountConfirmationRepository.save(patientAccountConfirmation);

            String link = "http://localhost:8082/patient/verifyAccount?token=" + token;

            gmailSenderService.sendMail(patient.getEmail(),
                    "Account Verification",
                    "Click the link below to verify your account\n" +
                            "<a href='" + link  + "'>Here</a>"
                    );

            return ResponseEntity.ok("An email has been sent to you for verification ");
        }
        else {
            return ResponseEntity.badRequest().body("Password mismatch!");
        }

    }

    public Patient viewProfile(Long id) {
        return patientRepository.findById(id).get();
    }

    public void editProfile(Long id, Patient patient) {
        Patient pat = patientRepository.findById(id).get();
        pat.setFirstName(patient.getFirstName());
        pat.setMiddleName(patient.getMiddleName());
        pat.setLastName(patient.getLastName());
        pat.setDateOfBirth(patient.getDateOfBirth());
        pat.setEmail(patient.getEmail());
        pat.setCountry(patient.getCountry());
        pat.setState(patient.getState());
        pat.setLga(patient.getLga());
        pat.setGender(patient.getGender());
        pat.setAddress(patient.getAddress());
        pat.setPhoneNumber(patient.getPhoneNumber());
        pat.setRole(valueOf(PATIENT.name()));
        patientRepository.save(pat);
    }

    public void deleteProfile(Long id) {
        patientRepository.deleteById(id);
    }

    public String verifyAccount(String token) {
        PatientAccountConfirmation patientAccountConfirmation
        = patientAccountConfirmationRepository.findByToken(token);

        Calendar calendar =
                Calendar.getInstance();

        if (patientAccountConfirmation == null) {
            return "No token available for verification";
        }
        if (patientAccountConfirmation.getExpirationTime().getTime() - calendar.getTime().getTime() <= 0) {
            patientAccountConfirmationRepository.delete(patientAccountConfirmation);
            return "Token has expired";
        }

        Patient patient
                = patientAccountConfirmation.getPatient();
        patient.setEnabled(true);
        patientRepository.save(patient);
        return "Account verified";
        }


    public String resetPassword(String email) throws MessagingException {
        Patient patient = patientRepository.findByEmail(email);
        String token = UUID.randomUUID().toString();
        PatientPasswordResetToken patientPasswordResetToken =
                new PatientPasswordResetToken(patient, token);

        String link = "http://localhost:3000/patient/changePassword?token="+token;

        gmailSenderService.sendMail(email,
                "Password Reset",
                "Click this link to change your password <a href='" + link + "'>Here</a>"
        );

        patientPasswordResetTokenRepository.save(patientPasswordResetToken);
        return "Check your email to reset your password";
    }

    public String changePassword(String token, PatientPasswordResetModel patientPasswordResetModel) {
        PatientPasswordResetToken patientPasswordResetToken =
                patientPasswordResetTokenRepository.findByToken(token);

        Calendar calendar =
                Calendar.getInstance();

        if (patientPasswordResetToken == null){
            return "No token available for verification";
        }
        if (patientPasswordResetToken.getExpirationTime().getTime() - calendar.getTime().getTime() <=0){
//            patientPasswordResetTokenRepository.delete(patientPasswordResetToken);
            return "Token has expired";
        }

        Patient patient =
                patientPasswordResetToken.getPatient();
        patient.setPassword(passwordEncoder.encode(patientPasswordResetModel.getNewPassword()));
        patient.setConfirmPassword(passwordEncoder.encode(patientPasswordResetModel.getReEnterNewPassword()));
        patientRepository.save(patient);
//        patientPasswordResetTokenRepository.delete(patientPasswordResetToken);
        return "Password changed Successfully";
    }

    public String updateProfilePicture(Long id, MultipartFile profilePicture) {
        Patient patient =
                patientRepository.findById(id).get();
        patient.setProfilePhoto(profilePicture.getOriginalFilename());
        patientRepository.save(patient);
        return "Profile Picture Updated";
    }


    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }
}

//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Patient userByUsername = patientRepository.findUserByUsername(username);
//        return userByUsername;
//    }

