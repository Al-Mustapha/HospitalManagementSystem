package com.example.HMS.Security;

import com.google.common.collect.Sets;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.example.HMS.Security.UserPermissions.*;

public enum UserRole {
    PATIENT(Sets.newHashSet(
            PATIENT_WRITE,
            PATIENT_READ,
            PATIENT_DELETE,
            PATIENT_UPDATE)),

    DOCTOR(
            Sets.newHashSet(
            DOCTOR_WRITE,
            DOCTOR_READ,
            DOCTOR_UPDATE,
            DOCTOR_DELETE
            )
            ),
    PHARMACIST(
            Sets.newHashSet(
                    PHARMACIST_WRITE,
                    PHARMACIST_READ,
                    PHARMACIST_UPDATE,
                    PHARMACIST_DELETE
            )),
            NURSE(
                    Sets.newHashSet(
                            NURSE_WRITE,
                            NURSE_READ,
                            NURSE_UPDATE,
                            NURSE_DELETE
                    )
    ),

    LABORATORIST(
            Sets.newHashSet(
            LABORATORIST_WRITE,
            LABORATORIST_READ,
            LABORATORIST_UPDATE,
            LABORATORIST_DELETE
            )
    )
    ;


    private final Set<UserPermissions> permissionsSet;

    UserRole(Set<UserPermissions> permissionsSet) {
        this.permissionsSet = permissionsSet;
    }

    public Set<UserPermissions> getPermissionsSet(){
        return permissionsSet;
    }

    public List<SimpleGrantedAuthority> getAuthorities(){
        List perms = getPermissionsSet().stream()
                .map(permissions -> new SimpleGrantedAuthority(permissions.name()))
                .collect(Collectors.toList());
        perms.add(new SimpleGrantedAuthority("ROLE_" + this.name()));

        return perms;
    }

}
