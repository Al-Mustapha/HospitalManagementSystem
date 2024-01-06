package com.example.HMS.Users.Laboratorist;

import com.example.HMS.GmailSenderService;
import com.example.HMS.Security.UserRole;
import com.example.HMS.Users.Laboratorist.LaboratoristAccountConfirmation.LaboratoristAccountConfirmation;
import com.example.HMS.Users.Laboratorist.LaboratoristAccountConfirmation.LaboratoristAccountConfirmationRepository;
import com.example.HMS.Users.Laboratorist.LaboratoristPasswordResetToken.LaboratoristPasswordResetModel;
import com.example.HMS.Users.Laboratorist.LaboratoristPasswordResetToken.LaboratoristPasswordResetToken;
import com.example.HMS.Users.Laboratorist.LaboratoristPasswordResetToken.LaboratoristPasswordResetTokenRepository;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class LaboratoristService {

    @Autowired
    public BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    public LaboratoristPasswordResetTokenRepository laboratoristPasswordResetTokenRepository;
    @Autowired
    public LaboratoristRepository laboratoristRepository;

    @Autowired
    private GmailSenderService gmailSenderService;

    @Autowired
    public LaboratoristAccountConfirmationRepository laboratoristAccountConfirmationRepository;

    public String createProfile(LaboratoristModel laboratoristModel) throws MessagingException {
        String token = UUID.randomUUID().toString();
        Laboratorist laboratorist =
                new Laboratorist();

        if (laboratoristModel.getPassword().equals(laboratoristModel.getConfirmPassword())){
            laboratorist.setEmail(laboratoristModel.getEmail());
            laboratorist.setCountry(laboratoristModel.getCountry());
            laboratorist.setAddress(laboratoristModel.getAddress());
            laboratorist.setDateOfBirth(laboratoristModel.getDateOfBirth());
            laboratorist.setFirstName(laboratoristModel.getFirstName());
            laboratorist.setMiddleName(laboratoristModel.getMiddleName());
            laboratorist.setLastName(laboratoristModel.getLastName());
            laboratorist.setGender(laboratoristModel.getGender());
            laboratorist.setState(laboratoristModel.getState());
            laboratorist.setLga(laboratoristModel.getLga());
            laboratorist.setPhoneNumber(laboratoristModel.getPhoneNumber());
            laboratorist.setQualification(laboratoristModel.getQualification());
            laboratorist.setUsername(laboratoristModel.getUsername());
            laboratorist.setPassword(laboratoristModel.getPassword());
            laboratorist.setConfirmPassword(laboratoristModel.getConfirmPassword());
            laboratorist.setRole(UserRole.LABORATORIST);
            LaboratoristAccountConfirmation laboratoristAccountConfirmation =
                    new LaboratoristAccountConfirmation(laboratorist, token);
            laboratoristAccountConfirmationRepository.save(laboratoristAccountConfirmation);
            String link = "http://localhost:8082/laboratorist/verifyAccount?token="
                    + token;
            gmailSenderService.sendMail(
                    laboratoristModel.getEmail(),
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

        LaboratoristAccountConfirmation laboratoristAccountConfirmation =
                laboratoristAccountConfirmationRepository.findByToken(token);
        if (token == null){
            return "No token available for verification";
        }
        else if (laboratoristAccountConfirmation.getExpirationTime().getTime() - calendar.getTime().getTime() <=0){
            laboratoristAccountConfirmationRepository.delete(laboratoristAccountConfirmation);
            return "The token has expired";
        }

        Laboratorist laboratorist =
                laboratoristAccountConfirmation.getLaboratorist();
//        pharmacistAccountConfirmationRepository.delete(pharmacistAccountConfirmation);
        laboratorist.setEnabled(true);
        laboratoristRepository.save(laboratorist);
        return "Account Verified";
    }

    public String resetPassword(String email) throws MessagingException {
        Optional<Laboratorist> pharmacist =
                Optional.ofNullable(laboratoristRepository.findByEmail(email));
        if (pharmacist.isPresent()){
            String token = UUID.randomUUID().toString();
            LaboratoristPasswordResetToken laboratoristPasswordResetToken =
                    new LaboratoristPasswordResetToken(pharmacist.get(), token);
            laboratoristPasswordResetTokenRepository.save(laboratoristPasswordResetToken);

            String link = "http://localhost:8082/laboratorist/changePassword?token="
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

    public String changePassword(String token, LaboratoristPasswordResetModel laboratoristPasswordResetModel) {
        LaboratoristPasswordResetToken laboratoristPasswordResetToken =
                laboratoristPasswordResetTokenRepository.findByToken(token);
        Laboratorist laboratorist =
                laboratoristPasswordResetToken.getLaboratorist();
        laboratorist.setPassword(laboratoristPasswordResetModel.getPassword());
        laboratorist.setConfirmPassword(laboratoristPasswordResetModel.getConfirmPassword());
        laboratoristRepository.save(laboratorist);
        return "Password Changed Successfully";
    }

    public List<Laboratorist> getAllLaboratorists() {
        return laboratoristRepository.findAll();
    }

    public String updateProfilePicture(Long id, MultipartFile profilePicture) {
        Laboratorist laboratorist =
                laboratoristRepository.findById(id).get();
        laboratorist.setProfilePhoto(profilePicture.getOriginalFilename());
        laboratoristRepository.save(laboratorist);
        return "Profile photo updated";
    }
}
