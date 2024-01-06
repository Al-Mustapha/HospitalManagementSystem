package com.example.HMS.Appointment;

import jakarta.servlet.http.HttpServletRequest;
import org.apache.catalina.LifecycleState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/appointment/")
@CrossOrigin(origins = "http://localhost:3000")
public class AppointmentController {

    @Autowired
    public AppointmentService appointmentService;

    @GetMapping("viewUserAppointment/{id}")
    public Appointment viewUserAppointment(@PathVariable("id") Long id){
        return appointmentService.viewUserAppointment(id);
    }

    @GetMapping("viewAllAppointments")
    @PreAuthorize("hasRole('DOCTOR')")
    public List<Appointment> viewAllAppointments(){
        return appointmentService.viewAllAppointments();
    }

    @PutMapping("editAppointmentStatus/{id}")
    @PreAuthorize("hasAnyRole('DOCTOR')")
    public String editAppointmentStatus(@PathVariable("id") Long id, @RequestParam("appointmentStatus") String status){
        return appointmentService.editAppointmentStatus(id, status);
    }

    @PutMapping("editAppointment/{id}")
    @PreAuthorize("hasRole('PATIENT')")
    public String editAppointment(@PathVariable("id") Long id, @RequestBody AppointmentModel appointmentModel){
        return appointmentService.editAppointment(id, appointmentModel);
    }

    @PostMapping("createAppointment")
//    @PreAuthorize("hasRole('PATIENT')")
    public Appointment createAppointment(@RequestBody Appointment appointment,
                                    HttpServletRequest request){
        return appointmentService.createAppointment(appointment, request);
    }

    @DeleteMapping("deleteAnAppointment/{id}")
    @PreAuthorize("hasAnyRole('DOCTOR','PATIENT')")
    public String deleteAnAppointment(@PathVariable("id") Long id){
        return appointmentService.deleteAnAppointment(id);
    }

    @GetMapping("fetchUserAppointments/{patientId}")
    public List<Appointment> fetchAppointmentsByPatientID(@PathVariable("patientId") Long patientId){
        return appointmentService.fetchAppointmentsByPatientId(patientId);
    }





}
