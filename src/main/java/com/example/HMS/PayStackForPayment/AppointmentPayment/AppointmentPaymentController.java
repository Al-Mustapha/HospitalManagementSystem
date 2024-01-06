package com.example.HMS.PayStackForPayment.AppointmentPayment;

import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.servlet.http.HttpServletRequest;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/appointment/")
@CrossOrigin(origins = "http://localhost:3000")
public class AppointmentPaymentController {
    @Autowired
    private AppointmentPaymentService appointmentPaymentService;

    @PostMapping("pay/{id}")
    public ResponseEntity<String> pay(@PathVariable("id") Long userAppointmentId,
                                      HttpServletRequest request
                      ) throws JSONException, JsonProcessingException {
        return appointmentPaymentService.pay(userAppointmentId, request);
    }

    @GetMapping("verify/{reference}")
    public ResponseEntity<String> verifyPayment(@PathVariable("reference") String reference) throws JSONException {
        return appointmentPaymentService.verifyPayment(reference);
    }

    @PostMapping("paystack-webhook")
    public ResponseEntity<String> handleWebhook(@RequestBody String payload) throws JSONException {
        return appointmentPaymentService.handleWebhook(payload);
    }
}
