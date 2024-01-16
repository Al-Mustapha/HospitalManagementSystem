package com.example.HMS.Users.Patient;

import com.example.HMS.Users.Patient.PatientPasswordResetToken.PatientPasswordResetModel;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/patient/")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(origins = "http://localhost:3000")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @PostMapping("createProfile")
    public ResponseEntity<String> createProfile(@RequestBody Patient patient, HttpServletRequest request) throws MessagingException {
       return patientService.createAccount(patient);
    }

    @GetMapping("viewProfile/{id}")
    @PreAuthorize("hasAnyRole('PATIENT','DOCTOR')")
    public Patient viewProfile(@PathVariable("id") Long id){
        Patient patient = patientService.viewProfile(id);
        return patient;
    }

    @PutMapping("editProfile/{id}")
    public String editProfile(@PathVariable("id") Long id, @RequestBody PatientModel patientModel){
        patientService.editProfile(id, patientModel);
        return "Profile updated successfully";
    }

    @DeleteMapping("deleteProfile/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteProfile(@PathVariable("id") Long id){
        patientService.deleteProfile(id);
        return "Account deleted successfully";
    }

    @GetMapping("verifyAccount")
    public String verifyAccount(@RequestParam("token") String token){
        return patientService.verifyAccount(token);
    }

    @PostMapping("resetPassword/{email}")
    public String resetPassword(@PathVariable("email") String email) throws MessagingException {
        return patientService.resetPassword(email);
    }


    @PostMapping("changePassword")
    public String changePassword(@RequestParam("token") String token,
                                 @RequestBody PatientPasswordResetModel patientPasswordResetModel){
        return patientService.changePassword(token, patientPasswordResetModel);

    }

    @PutMapping("updateProfilePicture/{id}")
    public String updateProfilePicture(@PathVariable("id") Long id, @RequestParam("photo") MultipartFile profilePicture){
     return patientService.updateProfilePicture(id, profilePicture);
    }

    @GetMapping("size")
    public Long getSize(){
        return Long.valueOf(patientService.getAllPatients().size());
    }

}
