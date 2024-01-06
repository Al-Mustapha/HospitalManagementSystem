package com.example.HMS.Users.Nurse;

import lombok.Data;

@Data
public class NurseModel {
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
