package com.example.HMS.Users.Pharmacist.PharmacistPasswordResetToken;

import com.example.HMS.Users.Pharmacist.Pharmacist;
import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Calendar;
import java.util.Date;

@Entity
@Data
@RequiredArgsConstructor
public class PharmacistPasswordResetToken {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false, unique = true)
    private String token;
    private Date expirationTime;

    @ManyToOne(
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    @JoinColumn(
            name = "pharmacist_id",
            nullable = false
    )
    private Pharmacist pharmacist;


    public PharmacistPasswordResetToken(Pharmacist pharmacist, String token){
        this.pharmacist = pharmacist;
        this.token = token;
        this.expirationTime = calculateExpirationTime();
    }

    private Date calculateExpirationTime() {
        Calendar calendar =
                Calendar.getInstance();
        calendar.setTimeInMillis(new Date().getTime());
        calendar.add(calendar.MINUTE, 10);
        return new Date(calendar.getTime().getTime());
    }
}
