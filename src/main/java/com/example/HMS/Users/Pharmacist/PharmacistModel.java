package com.example.HMS.Users.Pharmacist;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class PharmacistModel {
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
    private String username;
    private String password;
    private String confirmPassword;
    private String qualification;

    private String profilePhoto;
}
