package com.example.HMS.Users.Nurse;

import com.example.HMS.GmailSenderService;
import com.example.HMS.Security.UserRole;
import com.example.HMS.Users.Nurse.NurseAccountConfirmation.NurseAccountConfirmation;
import com.example.HMS.Users.Nurse.NurseAccountConfirmation.NurseAccountConfirmationRepository;
import com.example.HMS.Users.Nurse.NursePasswordResetToken.NursePasswordResetModel;
import com.example.HMS.Users.Nurse.NursePasswordResetToken.NursePasswordResetToken;
import com.example.HMS.Users.Nurse.NursePasswordResetToken.NursePasswordResetTokenRepository;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@Service
public class NurseService {

    @Autowired
    public BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    public NursePasswordResetTokenRepository nursePasswordResetTokenRepository;
    @Autowired
    public NurseRepository nurseRepository;

    @Autowired
    private GmailSenderService gmailSenderService;

    @Autowired
    public NurseAccountConfirmationRepository nurseAccountConfirmationRepository;

    public String createProfile(NurseModel nurseModel) throws MessagingException {
        String token = UUID.randomUUID().toString();
        Nurse nurse =
                new Nurse();

        if (nurseModel.getPassword().equals(nurseModel.getConfirmPassword())){
            nurse.setEmail(nurseModel.getEmail());
            nurse.setCountry(nurseModel.getCountry());
            nurse.setAddress(nurseModel.getAddress());
            nurse.setDateOfBirth(nurseModel.getDateOfBirth());
            nurse.setFirstName(nurseModel.getFirstName());
            nurse.setMiddleName(nurseModel.getMiddleName());
            nurse.setLastName(nurseModel.getLastName());
            nurse.setGender(nurseModel.getGender());
            nurse.setState(nurseModel.getState());
            nurse.setLga(nurseModel.getLga());
            nurse.setPhoneNumber(nurseModel.getPhoneNumber());
            nurse.setQualification(nurseModel.getQualification());
            nurse.setUsername(nurseModel.getUsername());
            nurse.setPassword(nurseModel.getPassword());
            nurse.setConfirmPassword(nurseModel.getConfirmPassword());
            nurse.setRole(UserRole.NURSE);
            NurseAccountConfirmation nurseAccountConfirmation =
                    new NurseAccountConfirmation(nurse, token);
            nurseAccountConfirmationRepository.save(nurseAccountConfirmation);
            String link = "http://localhost:8082/nurse/verifyAccount?token="
                    + token;
            gmailSenderService.sendMail(
                    nurseModel.getEmail(),
                    "Verify Account",
                    "<p>Click <a href='" + link + "'>here</a>to verify your account</p>"
            );
            return "Check your email for verification"
                    ;
        }
        else {
            return "Password mismatch";
        }

    }

    public String verifyAccount(String token) {
        Calendar calendar =
                Calendar.getInstance();

        NurseAccountConfirmation nurseAccountConfirmation =
                nurseAccountConfirmationRepository.findByToken(token);
        if (token == null){
            return "No token available for verification";
        }
        else if (nurseAccountConfirmation.getExpirationTime().getTime() - calendar.getTime().getTime() <=0){
            nurseAccountConfirmationRepository.delete(nurseAccountConfirmation);
            return "The token has expired";
        }

        Nurse nurse =
                nurseAccountConfirmation.getNurse();
//        pharmacistAccountConfirmationRepository.delete(pharmacistAccountConfirmation);
        nurse.setEnabled(true);
        nurseRepository.save(nurse);
        return "Account Verified";
    }

    public String resetPassword(String email) throws MessagingException {
        Optional<Nurse> pharmacist =
                Optional.ofNullable(nurseRepository.findByEmail(email));
        if (pharmacist.isPresent()){
            String token = UUID.randomUUID().toString();
            NursePasswordResetToken nursePasswordResetToken =
                    new NursePasswordResetToken(pharmacist.get(), token);
            nursePasswordResetTokenRepository.save(nursePasswordResetToken);

            String link = "http://localhost:8082/nurse/changePassword?token="
                    + token;
            gmailSenderService.sendMail(email,
                    "Reset Password",
                    "<p>Click <a href='" + link + "'>here </a>to reset your password</p>");
            return "Check your email for password reset link";
        }
        else {
            throw new UsernameNotFoundException("No user with such email exists");
        }
    }

    public String changePassword(String token, NursePasswordResetModel nursePasswordResetModel) {
        NursePasswordResetToken nursePasswordResetToken =
                nursePasswordResetTokenRepository.findByToken(token);
        Nurse nurse =
                nursePasswordResetToken.getNurse();
        nurse.setPassword(nursePasswordResetModel.getPassword());
        nurse.setConfirmPassword(nursePasswordResetModel.getConfirmPassword());
        nurseRepository.save(nurse);
        return "Password Changed Successfully";
    }

    public List<Nurse> getAllNurses() {
        return nurseRepository.findAll();
    }

    public String updateProfilePicture(Long id, MultipartFile profilePicture) {
        Nurse nurse =
                nurseRepository.findById(id).get();
        nurse.setProfilePhoto(profilePicture.getOriginalFilename());
        nurseRepository.save(nurse);
        return "Profile photo updated";
    }
}
