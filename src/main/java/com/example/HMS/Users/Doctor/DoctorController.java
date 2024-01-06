package com.example.HMS.Users.Doctor;

import com.example.HMS.Users.Doctor.DoctorPasswordResetToken.DoctorPasswordReset;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.awt.print.Pageable;
import java.util.List;

@RestController
@RequestMapping("/doctor/")
@CrossOrigin(origins = "http://localhost:3000")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @PostMapping("createProfile")
    public ResponseEntity<String> createProfile(@RequestBody Doctor doctor) throws MessagingException {
       return doctorService.createProfile(doctor);
    }

    @GetMapping("viewProfile/{id}")
    public Doctor viewProfile(@PathVariable("id") Long id){
        return doctorService.viewProfile(id);
    }

    @PutMapping("editProfile/{id}")
    public String editProfile(@PathVariable("id") Long id, @RequestBody Doctor doctor){
        doctorService.editProfile(id, doctor);
        return "Account updated successfully";
    }

    @DeleteMapping("deleteProfile/{id}")
    public String deleteProfile(@PathVariable("id") Long id){
        doctorService.deleteProfile(id);
        return "Account deleted successfully";
    }

    @GetMapping("verifyAccount")
    public String verifyAccount(@RequestParam("token") String token){
        return doctorService.verifyAccount(token);
    }

    @GetMapping("viewAllDoctors")
    public List<Doctor> viewAllDoctors(){
        return doctorService.viewAllDoctors();
    }

    @PostMapping("resetPassword/{email}")
    public String resetPassword(@PathVariable("email") String email) throws MessagingException {
        return doctorService.resetPassword(email);
    }

    @PostMapping("changePassword")
    public String changePassword(@RequestParam("token") String token,
                                 @RequestBody DoctorPasswordReset doctorPasswordReset){
        return doctorService.changePassword(token, doctorPasswordReset);
    }

    @PutMapping("updateProfilePicture/{id}")
    public String updateProfilePicture(@PathVariable("id") Long id, @RequestParam("photo") MultipartFile profilePicture){
        return doctorService.updateProfilePicture(id, profilePicture);
    }


    @GetMapping("size")
    public Long doctorsCount(){
       return Long.valueOf(doctorService.viewAllDoctors().size());
    }

}
