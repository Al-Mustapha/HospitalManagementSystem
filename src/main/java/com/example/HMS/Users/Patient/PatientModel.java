package com.example.HMS.Users.Patient;

import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PatientModel {
    private String firstName;
    private String middleName;
    private String lastName;
    private String dateOfBirth;
    private String email;
    private String country;
    private String state;
    private String lga;
    private String gender;
    private String address;
    private String phoneNumber;
}
