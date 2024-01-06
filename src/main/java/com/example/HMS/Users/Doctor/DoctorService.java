package com.example.HMS.Users.Doctor;

import com.example.HMS.GmailSenderService;
import com.example.HMS.Users.Doctor.DoctorAccountConfirmation.DoctorAccountConfirmation;
import com.example.HMS.Users.Doctor.DoctorAccountConfirmation.DoctorAccountConfirmationRepository;
import com.example.HMS.Users.Doctor.DoctorPasswordResetToken.DoctorPasswordReset;
import com.example.HMS.Users.Doctor.DoctorPasswordResetToken.DoctorPasswordResetToken;
import com.example.HMS.Users.Doctor.DoctorPasswordResetToken.DoctorPasswordResetTokenRepository;
import com.example.HMS.Security.UserRole;
import jakarta.mail.MessagingException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.example.HMS.Security.UserRole.*;

@Service
public class DoctorService implements UserDetailsService {

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private GmailSenderService gmailSenderService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public DoctorAccountConfirmationRepository doctorAccountConfirmationRepository;

    @Autowired
    public DoctorPasswordResetTokenRepository doctorPasswordResetTokenRepository;
    public ResponseEntity<String> createProfile(Doctor doctor) throws MessagingException {

        String token = UUID.randomUUID().toString();
        if (doctor.getPassword().equals(doctor.getConfirmPassword())){
            doctor.setPassword(passwordEncoder.encode(doctor.getPassword()));
            doctor.setConfirmPassword(passwordEncoder.encode(doctor.getConfirmPassword()));
            doctor.setRole(UserRole.valueOf(DOCTOR.name()));

            DoctorAccountConfirmation doctorAccountConfirmation =
                    new DoctorAccountConfirmation(doctor, token);
            doctorAccountConfirmationRepository.save(doctorAccountConfirmation);

            String link = "http://localhost:8082/doctor/verifyAccount?token=" + token;

            gmailSenderService.sendMail(
                    doctor.getEmail(),
                    "Verify account",
                    "<p> Click <a href='" + link + "'>here</a> to verify your account</p>"
            );
            return ResponseEntity.ok("Check your email for a verification"
                    );
        }
        else {
            return ResponseEntity.badRequest().body("Password mismatch!");
        }

    }

    public Doctor viewProfile(Long id) {
        Doctor doctor = doctorRepository.findById(id).get();
        return doctor;
    }

    public void editProfile(Long id, Doctor doctor) {
        Doctor doc = doctorRepository.findById(id).get();
        BeanUtils.copyProperties(doctor, doc);
        doctorRepository.save(doc);
    }

    public void deleteProfile(Long id) {
        doctorRepository.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails userByUsername = doctorRepository.findUserByUsername(username);
        return userByUsername;
    }

    public String verifyAccount(String token) {
        DoctorAccountConfirmation doctorAccountConfirmation =
                doctorAccountConfirmationRepository.findByToken(token);

        Calendar calendar =
                Calendar.getInstance();

        if(token.equals("")){
            return "No token provided for verification";
        }
        if (doctorAccountConfirmation.getExpirationTime().getTime() - calendar.getTime().getTime() >=0 ){
            doctorAccountConfirmationRepository.delete(doctorAccountConfirmation);
            return "Token has expired";
        }

        Doctor doctor =
                doctorAccountConfirmation.getDoctor();
        doctor.setEnabled(true);
        doctorRepository.save(doctor);
        return "Account verified";
    }

    public List<Doctor> viewAllDoctors() {
        return doctorRepository.findAll();
    }

    public String resetPassword(String email) throws MessagingException {
        String token = UUID.randomUUID().toString();
        Optional<Doctor> doctor =
                Optional.ofNullable(doctorRepository.findByEmail(email));
        if (doctor.isPresent()){
            DoctorPasswordResetToken doctorPasswordResetToken =
                    new DoctorPasswordResetToken(doctor.get(), token);
            doctorPasswordResetTokenRepository.save(doctorPasswordResetToken);

            String link = "http://localhost:3000/doctor/changePassword?token="+token;

            gmailSenderService.sendMail(email,
                    "Reset password",
                    "<p>Click <a href='" + link + "'>here</a> to change your password</p>");
        }
        return "Check your email for a link to change password";
    }

    public String changePassword(String token, DoctorPasswordReset doctorPasswordReset) {
        DoctorPasswordResetToken doctorPasswordResetToken =
                doctorPasswordResetTokenRepository.findByToken(token);

        Calendar calendar =
                Calendar.getInstance();


        if (doctorPasswordResetToken == null) {
            return "No reset permitted";
        }
        if (doctorPasswordResetToken.getExpirationTime().getTime() - calendar.getTime().getTime() <= 0){
//            doctorPasswordResetTokenRepository.delete(doctorPasswordResetToken);
            return "Token has expired";
        }

        Doctor doctor =
                doctorPasswordResetToken.getDoctor();
//        doctorPasswordResetTokenRepository.delete(doctorPasswordResetToken);
        doctor.setPassword(passwordEncoder.encode(doctorPasswordReset.getNewPassword()));
        doctor.setConfirmPassword(passwordEncoder.encode(doctorPasswordReset.getReEnterNewPassword()));
        doctorRepository.save(doctor);
        return "Password Successfully changed";
    }

    public String updateProfilePicture(Long id, MultipartFile profilePicture) {
        Doctor doctor =
                doctorRepository.findById(id).get();
        doctor.setProfilePhoto(profilePicture.getOriginalFilename());
        doctorRepository.save(doctor);
        return "Profile photo updated";
    }
}
