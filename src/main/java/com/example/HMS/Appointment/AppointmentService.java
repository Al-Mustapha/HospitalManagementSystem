package com.example.HMS.Appointment;

import com.example.HMS.Authentication.JwtUtils;
import com.example.HMS.PayStackForPayment.AppointmentPayment.AppointmentPayment;
import com.example.HMS.PayStackForPayment.AppointmentPayment.AppointmentPaymentService;
import com.example.HMS.Security.AppointmentPaymentStatus;
import com.example.HMS.Users.Doctor.Doctor;
import com.example.HMS.Users.Doctor.DoctorRepository;
import com.example.HMS.Users.Patient.Patient;
import com.example.HMS.Users.Patient.PatientRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentPaymentService appointmentPaymentService;
    @Autowired
    public AppointmentRepository appointmentRepository;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    public DoctorRepository doctorRepository;

    public Appointment viewUserAppointment(Long id) {
        return appointmentRepository.findById(id).get();
    }

    public List<Appointment> viewAllAppointments() {
        return appointmentRepository.findAll();
    }

    public String editAppointment(Long id, AppointmentModel appointment) {
        Appointment appo = appointmentRepository.findById(id).get();
        appo.setAppointmentDate(appointment.getAppointmentDate());
        appo.setAppointmentTime(appointment.getAppointmentTime());
        appointmentRepository.save(appo);
        return "Appointment updated";
    }

    public Appointment createAppointment(Appointment appointment, HttpServletRequest request) {

        String authorization = request.getHeader("Authorization");
        String token = authorization.substring(7);

        String patientUsername =
                jwtUtils.extractUsername(token);

        Patient patient =
                patientRepository.findUserByUsername(patientUsername);

//        Doctor doctor = doctorRepository.findById(id).get();
//        appointment.setDoctor(doctor);
        appointment.setAppointmentStatus("Pending");
        appointment.setPaymentStatus(AppointmentPaymentStatus.Not_paid);
        appointment.setPatient(patient);
        appointmentRepository.save(appointment);
        return appointment;
    }

    public String deleteAnAppointment(Long id) {
        appointmentRepository.deleteById(id);
        return "Appointment deleted Successfully";
    }

    public String editAppointmentStatus(Long id, String status) {
        Appointment appointment =
                appointmentRepository.findById(id).get();
        appointment.setAppointmentStatus(status);
        appointmentRepository.save(appointment);
        return "Appointment status updated";
    }

    public List<Appointment> fetchAppointmentsByPatientId(Long patientId) {
        return appointmentRepository.findAppointmentsByPatientId(patientId);
    }
}
