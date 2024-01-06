package com.example.HMS.PayStackForPayment.AppointmentPayment;


import com.example.HMS.Security.AppointmentPaymentStatus;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONException;
import org.json.JSONObject;
import com.example.HMS.Appointment.Appointment;
import com.example.HMS.Appointment.AppointmentRepository;
import com.example.HMS.Authentication.JwtUtils;
import com.example.HMS.Users.Patient.Patient;
import com.example.HMS.Users.Patient.PatientRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Base64;


@Service
public class AppointmentPaymentService {


    @Autowired
    private AppointmentPaymentRepository appointmentPaymentRepository;
    private RestTemplate restTemplate = new RestTemplate();

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private JwtUtils jwtUtils;

    private final ObjectMapper objectMapper = new ObjectMapper();
    @Value("${paystack.secretKey}")
    private String key;
    public ResponseEntity<String> pay(Long appointmentId, HttpServletRequest request) throws JSONException, JsonProcessingException {
        String url = "https://api.paystack.co/transaction/initialize";
        org.springframework.http.HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setBearerAuth(key);
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        Appointment appointment =
                appointmentRepository.findById(appointmentId).get();

        String header  = request.getHeader("authorization");
        String token = header.substring(7);
        String username = jwtUtils.extractUsername(token);
        Patient patient =
                patientRepository.findUserByUsername(username);
        AppointmentPayment appointmentPayment =
                new AppointmentPayment();
        appointmentPayment.setAmount(700*100);
        appointmentPayment.setEmail(patient.getEmail());
        appointmentPayment.setName("Appointment Booking");

        HttpEntity<AppointmentPayment> entity = new HttpEntity<>(appointmentPayment, httpHeaders);
         ResponseEntity<String> responseEntity = restTemplate.exchange(
           url,
           HttpMethod.POST,
           entity,
           String.class
         );
         JSONObject jsonObject = new JSONObject(responseEntity.getBody());
//        String checkout = jsonObject.getJSONObject("data").getString("authorization_url");
        appointmentPayment.setReference(jsonObject.getJSONObject("data").getString("reference"));
        appointmentPayment.setStatus(AppointmentPaymentStatus.Not_paid);
        appointmentPayment.setPaymentDate(LocalDate.now());
        appointmentPayment.setPaymentTime(LocalDateTime.now());
        appointmentPayment.setAppointments(appointment);

        appointmentPaymentRepository.save(appointmentPayment);

         if (responseEntity.getStatusCode() == HttpStatus.OK){
             return ResponseEntity.ok(responseEntity.getBody());
         }
         else {
             return new ResponseEntity<String>("Payment initialization failed", HttpStatus.INTERNAL_SERVER_ERROR);
         }
    }

    public ResponseEntity<String> verifyPayment(String reference) throws JSONException {
        String url = "https://api.paystack.co/transaction/verify";
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setBearerAuth(key);
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<>(httpHeaders);

        AppointmentPayment appointmentPayment =
                appointmentPaymentRepository.findByReference(reference);

        String verifyPayment = "https://api.paystack.co/transaction/verify/" + reference;
        ResponseEntity<String> verifyResponse =
                restTemplate.exchange(
                        verifyPayment,
                        HttpMethod.GET,
                        entity,
                        String.class
                );
        JSONObject jsonObject = new JSONObject(verifyResponse.getBody());
        String status = jsonObject.getJSONObject("data").getString("status");
        if (verifyResponse.getStatusCode() == HttpStatus.OK){
            Appointment appointment =
                    appointmentPayment.getAppointments();
            appointment.setPaymentStatus(AppointmentPaymentStatus.Paid);
            appointmentPayment.setStatus(AppointmentPaymentStatus.Paid);

            AppointmentPayment appointmentPayment1 =
                    new AppointmentPayment(appointment, appointmentPayment);
            appointmentPaymentRepository.save(appointmentPayment1);

            return ResponseEntity.ok(verifyResponse.getBody());
        }
        else {
            return ResponseEntity.internalServerError().body("Payment verification failed");
        }

    }

    public ResponseEntity<String> handleWebhook(String payload) throws JSONException {
        JSONObject jsonObject = new JSONObject(payload);

        String status = jsonObject.getJSONObject("data").getString("status");
        if (status.equalsIgnoreCase("success")){
            AppointmentPayment appointmentPayment =
                    appointmentPaymentRepository.findByReference(
                            jsonObject.getJSONObject("data").getString("reference"));
            Appointment appointment =
                    appointmentPayment.getAppointments();
            appointmentPayment.setStatus(AppointmentPaymentStatus.Paid);
            appointment.setPaymentStatus(AppointmentPaymentStatus.Paid);


            appointmentRepository.save(appointment);
//            AppointmentPayment appointmentPayment1 =
//                    new AppointmentPayment(appointment, appointmentPayment);
            appointmentPaymentRepository.save(appointmentPayment);
        }


        System.out.println(payload);
        return ResponseEntity.ok(payload);
    }

}
