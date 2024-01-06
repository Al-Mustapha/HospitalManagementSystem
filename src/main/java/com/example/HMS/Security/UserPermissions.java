package com.example.HMS.Security;

public enum UserPermissions {
    PATIENT_READ("patient:read"),
    PATIENT_WRITE("patient:write"),
    PATIENT_DELETE("patient:delete"),
    PATIENT_UPDATE("patient:update"),

    DOCTOR_READ("doctor:read"),
    DOCTOR_WRITE("doctor:write"),
    DOCTOR_DELETE("doctor:delete"),
    DOCTOR_UPDATE("doctor:update"),

    PHARMACIST_READ("pharmacist:read"),
    PHARMACIST_WRITE("pharmacist:write"),
    PHARMACIST_DELETE("pharmacist:delete"),
    PHARMACIST_UPDATE("pharmacist:update"),

    NURSE_READ("nurse:read"),
    NURSE_WRITE("nurse:write"),
    NURSE_DELETE("nurse:delete"),
    NURSE_UPDATE("nurse:update"),

    LABORATORIST_READ("laboratorist:read"),
    LABORATORIST_WRITE("laboratorist:write"),
    LABORATORIST_DELETE("laboratorist:delete"),
    LABORATORIST_UPDATE("laboratorist:update");

    private final String permissions;


    UserPermissions(String permissions) {
        this.permissions = permissions;
    }
}
