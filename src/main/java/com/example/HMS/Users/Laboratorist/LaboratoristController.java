package com.example.HMS.Users.Laboratorist;

import com.example.HMS.Users.Laboratorist.LaboratoristPasswordResetToken.LaboratoristPasswordResetModel;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/laboratorist/")
public class LaboratoristController {

    @Autowired
    public LaboratoristService laboratoristService;

    @PostMapping("createProfile")
    public String createProfile(@RequestBody LaboratoristModel laboratoristModel) throws MessagingException {
        return laboratoristService.createProfile(laboratoristModel);
    }

    @GetMapping("verifyAccount")
    public String verifyAccount(@RequestParam("token") String token){
        return laboratoristService.verifyAccount(token);
    }

    @GetMapping("resetPassword/{email}")
    public String resetPassword(@PathVariable("email") String email) throws MessagingException {
        return laboratoristService.resetPassword(email);
    }

    @PutMapping("changePassword")
    public String changePassword(@RequestParam("token") String token,
                                 @RequestBody LaboratoristPasswordResetModel laboratoristPasswordResetModel){
        return laboratoristService.changePassword(token, laboratoristPasswordResetModel);
    }

    @PutMapping("updateProfilePicture/{id}")
    public String updateProfilePicture(@PathVariable("id") Long id, @RequestParam("photo") MultipartFile profilePicture){
        return laboratoristService.updateProfilePicture(id, profilePicture);
    }

    @GetMapping("size")
    public Long getSize(){
        return Long.valueOf(laboratoristService.getAllLaboratorists().size());
    }

}
