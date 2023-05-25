package com.friendfinder.entity;

import com.friendfinder.entity.types.UserGender;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String surname;
    private String email;
    private String password;

    @Column(name = "date_of_birth")
    private Date dateOfBirth;

    @Enumerated(value = EnumType.STRING)
    private UserGender gender;
    private String city;

    @ManyToOne
    private Country country;

    @Column(name = "profile_pic")
    private String profilePicture;

    @Column(name = "personal_information")
    private String personalInformation;

}
