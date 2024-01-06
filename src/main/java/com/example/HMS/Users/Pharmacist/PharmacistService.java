package com.example.HMS.Users.Pharmacist;

import com.example.HMS.GmailSenderService;
import com.example.HMS.Security.UserRole;
import com.example.HMS.Users.Pharmacist.PharmacistAccountConfirmation.PharmacistAccountConfirmation;
import com.example.HMS.Users.Pharmacist.PharmacistAccountConfirmation.PharmacistAccountConfirmationRepository;
import com.example.HMS.Users.Pharmacist.PharmacistPasswordResetToken.PharmacistPasswordResetModel;
import com.example.HMS.Users.Pharmacist.PharmacistPasswordResetToken.PharmacistPasswordResetToken;
import com.example.HMS.Users.Pharmacist.PharmacistPasswordResetToken.PharmacistPasswordResetTokenRepository;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@Service
public class PharmacistService {

    @Autowired
    public BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    public PharmacistPasswordResetTokenRepository pharmacistPasswordResetTokenRepository;
    @Autowired
    public PharmacistRepository pharmacistRepository;

    @Autowired
    private GmailSenderService gmailSenderService;

    @Autowired
    public PharmacistAccountConfirmationRepository pharmacistAccountConfirmationRepository;

    public String createProfile(PharmacistModel pharmacistModel) throws MessagingException {
        String token = UUID.randomUUID().toString();
        Pharmacist pharmacist =
                new Pharmacist();

        if (pharmacistModel.getPassword().equals(pharmacistModel.getConfirmPassword())){
            pharmacist.setEmail(pharmacistModel.getEmail());
            pharmacist.setCountry(pharmacistModel.getCountry());
            pharmacist.setAddress(pharmacistModel.getAddress());
            pharmacist.setDateOfBirth(pharmacistModel.getDateOfBirth());
            pharmacist.setFirstName(pharmacistModel.getFirstName());
            pharmacist.setMiddleName(pharmacistModel.getMiddleName());
            pharmacist.setLastName(pharmacistModel.getLastName());
            pharmacist.setGender(pharmacistModel.getGender());
            pharmacist.setState(pharmacistModel.getState());
            pharmacist.setLga(pharmacistModel.getLga());
            pharmacist.setPhoneNumber(pharmacistModel.getPhoneNumber());
            pharmacist.setQualification(pharmacistModel.getQualification());
            pharmacist.setUsername(pharmacistModel.getUsername());
            pharmacist.setPassword(pharmacistModel.getPassword());
            pharmacist.setConfirmPassword(pharmacistModel.getConfirmPassword());
            pharmacist.setRole(UserRole.PHARMACIST);
            PharmacistAccountConfirmation pharmacistAccountConfirmation =
                    new PharmacistAccountConfirmation(pharmacist, token);
            pharmacistAccountConfirmationRepository.save(pharmacistAccountConfirmation);
            String link = "http://localhost:8082/pharmacist/verifyAccount?token="
                    + token;
            gmailSenderService.sendMail(
                    pharmacistModel.getEmail(),
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

        PharmacistAccountConfirmation pharmacistAccountConfirmation =
                pharmacistAccountConfirmationRepository.findByToken(token);
        if (token == null){
            return "No token available for verification";
        }
        else if (pharmacistAccountConfirmation.getExpirationTime().getTime() - calendar.getTime().getTime() <=0){
            pharmacistAccountConfirmationRepository.delete(pharmacistAccountConfirmation);
            return "The token has expired";
        }

        Pharmacist pharmacist =
                pharmacistAccountConfirmation.getPharmacist();
//        pharmacistAccountConfirmationRepository.delete(pharmacistAccountConfirmation);
        pharmacist.setEnabled(true);
        pharmacistRepository.save(pharmacist);
        return "Account Verified";
    }

    public String resetPassword(String email) throws MessagingException {
        Optional<Pharmacist> pharmacist =
                Optional.ofNullable(pharmacistRepository.findByEmail(email));
        if (pharmacist.isPresent()){
            String token = UUID.randomUUID().toString();
            PharmacistPasswordResetToken pharmacistPasswordResetToken =
                    new PharmacistPasswordResetToken(pharmacist.get(), token);
            pharmacistPasswordResetTokenRepository.save(pharmacistPasswordResetToken);

            String link = "http://localhost:8082/pharmacist/changePassword?token="
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

    public String changePassword(String token, PharmacistPasswordResetModel pharmacistPasswordResetModel) {
        PharmacistPasswordResetToken pharmacistPasswordResetToken =
                pharmacistPasswordResetTokenRepository.findByToken(token);
        Pharmacist pharmacist =
                pharmacistPasswordResetToken.getPharmacist();
        pharmacist.setPassword(pharmacistPasswordResetModel.getPassword());
        pharmacist.setConfirmPassword(pharmacistPasswordResetModel.getConfirmPassword());
        pharmacistRepository.save(pharmacist);
        return "Password Changed Successfully";
    }

    public List<Pharmacist> getAllPharmacists() {
        return pharmacistRepository.findAll();
    }

    public String updateProfilePicture(Long id, MultipartFile profilePicture) {
        Pharmacist pharmacist =
                pharmacistRepository.findById(id).get();
        pharmacist.setProfilePhoto(profilePicture.getOriginalFilename());
        pharmacistRepository.save(pharmacist);
        return "Profile photo updated";
    }
}
