package com.example.HMS.Users.Nurse;

import com.example.HMS.Users.Nurse.NursePasswordResetToken.NursePasswordResetModel;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/nurse/")
public class NurseController {

    @Autowired
    public NurseService nurseService;

    @PostMapping("createProfile")
    public String createProfile(@RequestBody NurseModel nurseModel) throws MessagingException {
        return nurseService.createProfile(nurseModel);
    }

    @GetMapping("verifyAccount")
    public String verifyAccount(@RequestParam("token") String token){
        return nurseService.verifyAccount(token);
    }

    @GetMapping("resetPassword/{email}")
    public String resetPassword(@PathVariable("email") String email) throws MessagingException {
        return nurseService.resetPassword(email);
    }

    @PutMapping("changePassword")
    public String changePassword(@RequestParam("token") String token,
                                 @RequestBody NursePasswordResetModel nursePasswordResetModel){
        return nurseService.changePassword(token, nursePasswordResetModel);
    }

    @PutMapping("updateProfilePicture/{id}")
    public String updateProfilePicture(@PathVariable("id") Long id, @RequestParam("photo") MultipartFile profilePicture){
        return nurseService.updateProfilePicture(id, profilePicture);
    }

    @GetMapping("size")
    public Long getSize(){
        return Long.valueOf(nurseService.getAllNurses().size());
    }

}
