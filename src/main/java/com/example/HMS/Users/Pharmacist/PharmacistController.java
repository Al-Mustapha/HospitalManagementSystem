package com.example.HMS.Users.Pharmacist;

import com.example.HMS.Users.Pharmacist.PharmacistPasswordResetToken.PharmacistPasswordResetModel;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.http.HttpClient;

@RestController
@RequestMapping("/pharmacist/")
public class PharmacistController {

    @Autowired
    public PharmacistService pharmacistService;

    @PostMapping("createProfile")
    public String createProfile(@RequestBody PharmacistModel pharmacistModel) throws MessagingException {
        return pharmacistService.createProfile(pharmacistModel);
    }

    @GetMapping("verifyAccount")
    public String verifyAccount(@RequestParam("token") String token){
        return pharmacistService.verifyAccount(token);
    }

    @GetMapping("resetPassword/{email}")
    public String resetPassword(@PathVariable("email") String email) throws MessagingException {
        return pharmacistService.resetPassword(email);
    }

    @PutMapping("changePassword")
    public String changePassword(@RequestParam("token") String token,
                                 @RequestBody PharmacistPasswordResetModel pharmacistPasswordResetModel){
        return pharmacistService.changePassword(token, pharmacistPasswordResetModel);
    }

    @PutMapping("updateProfilePicture/{id}")
    public String updateProfilePicture(@PathVariable("id") Long id, @RequestParam("photo") MultipartFile profilePicture){
        return pharmacistService.updateProfilePicture(id, profilePicture);
    }

    @GetMapping("size")
    public Long getSize(){
        return Long.valueOf(pharmacistService.getAllPharmacists().size());
    }

}
