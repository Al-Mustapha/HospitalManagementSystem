package com.example.HMS.Prescription;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/prescription/")
public class PrescriptionController {
    @Autowired
    public PrescriptionService prescriptionService;

    @PostMapping("addPrescription")
    @PreAuthorize("hasRole('DOCTOR')")
    public String addPrescription(@RequestBody PrescriptionModel prescriptionModel, HttpServletRequest request){
       return prescriptionService.addPrescription(prescriptionModel, request);
    }

    @GetMapping("getPatientPrescription/{id}")
    @PreAuthorize("hasAnyRole('DOCTOR','PHARMACIST')")
    public Prescription getPatientPrescription(@PathVariable("id") Long id){
        return prescriptionService.getPatientPrescription(id);
    }

    @DeleteMapping("deletePatientPrescription/{id}")
    @PreAuthorize("hasAnyRole('DOCTOR','PHARMACIST')")
    public String deletePatientPrescription(@PathVariable("id") Long id){
        return prescriptionService.deletePatientPrescription(id);
    }

    @DeleteMapping("deleteAllPrescriptions")
    @PreAuthorize("hasRole('PHARMACIST')")
    public String deleteAllPrescriptions(){
        return prescriptionService.deleteAllPrescriptions();
    }

    @PutMapping("updatePatientPrescription/{id}")
    @PreAuthorize("hasRole('DOCTOR')")
    public String updatePatientPrescription(@PathVariable("id") Long id, @RequestBody PrescriptionModel prescriptionModel){
      return prescriptionService.updatePatientPrescription(id, prescriptionModel);
    }

}
