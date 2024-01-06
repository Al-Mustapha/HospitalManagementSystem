package com.example.HMS.MedicalRecord;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medicalRecord/")
public class MedicalRecordController {

    @Autowired
    public MedicalRecordService medicalRecordService;

    @PostMapping("addMedicalRecord")
    @PreAuthorize("hasRole('PATIENT')")
    public String addMedicalRecord(@RequestBody MedicalRecordModel medicalRecordModel, HttpServletRequest request){
        return medicalRecordService.addMedicalRecord(medicalRecordModel, request);
    }

    @GetMapping("getAllMedicalRecords")
    public List<MedicalRecord> getPatientMedicalRecords(){
        return medicalRecordService.getPatientMedicalRecords();
    }

    @GetMapping("getPatientMedicalRecord/{id}")
    public ResponseEntity<MedicalRecord> getPatientMedicalRecord(@PathVariable("id") Long id){
        return medicalRecordService.getPatientMedicalRecord(id);
    }

    @PutMapping("editPatientMedicalRecord/{id}")
    public String editPatientMedicalRecord(@PathVariable("id") Long id,
                                           @RequestBody MedicalRecordModel medicalRecordModel){
        return medicalRecordService.editPatientMedicalRecord(id, medicalRecordModel);

    }

}
