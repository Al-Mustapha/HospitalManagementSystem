package com.example.HMS.Error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@ResponseStatus
public class ExceptionHandlerResponse extends ResponseEntityExceptionHandler {
    @ExceptionHandler(AppointmentsNotFoundException.class)
    public ResponseEntity<ErrorMessage> appointmentsNotFoundException(AppointmentsNotFoundException appointmentsNotFoundException,
                                                                     WebRequest request){
        ErrorMessage errorMessage =
                new ErrorMessage(HttpStatus.NOT_FOUND, "No Available Appointments Found");
        return ResponseEntity.status(errorMessage.getStatus()).body(errorMessage);
    }
}
