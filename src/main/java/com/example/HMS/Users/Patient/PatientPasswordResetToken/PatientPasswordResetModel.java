package com.example.HMS.Users.Patient.PatientPasswordResetToken;

import lombok.Data;

@Data
public class PatientPasswordResetModel {
    private String newPassword;
    private String reEnterNewPassword;
}
