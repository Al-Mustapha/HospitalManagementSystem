package com.example.HMS.Users.Laboratorist.LaboratoristPasswordResetToken;

import com.example.HMS.Users.Laboratorist.Laboratorist;
import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Calendar;
import java.util.Date;

@Entity
@Data
@RequiredArgsConstructor
public class LaboratoristPasswordResetToken {
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
    private Laboratorist laboratorist;


    public LaboratoristPasswordResetToken(Laboratorist laboratorist, String token){
        this.laboratorist = laboratorist;
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
