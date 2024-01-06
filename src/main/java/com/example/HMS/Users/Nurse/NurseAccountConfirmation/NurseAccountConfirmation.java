package com.example.HMS.Users.Nurse.NurseAccountConfirmation;

import com.example.HMS.Users.Nurse.Nurse;
import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Calendar;
import java.util.Date;

@Data
@Entity
@RequiredArgsConstructor
public class NurseAccountConfirmation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false, unique = true)
    private String token;
    private Date expirationTime;

    @OneToOne(
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    @JoinColumn(
            name = "pharmacist_id",
            nullable = false
    )
    private Nurse nurse;


    public NurseAccountConfirmation(Nurse nurse, String token){
        this.nurse = nurse;
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
