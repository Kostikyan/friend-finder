package com.friendfinder.entity;

import com.friendfinder.entity.enums.UserGender;
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

    private UserGender gender;
    private String city;

    @ManyToOne
    private Country country;

    @Column(name = "profile_pic")
    private String profilePicture;

    @Column(name = "personal_information")
    private String personalInformation;

    // Education (ED)
    @Column(name = "ed_name")
    private String edName;

    @Column(name = "ed_from_date")
    private int edFromDate;

    @Column(name = "ed_to_date")
    private int edToDate;

    @Column(name = "ed_description")
    private String edDescription;


    // Work Experiences (WE)
    @Column(name = "we_name")
    private String weName;

    @Column(name = "we_designation")
    private String weDesignation;

    @Column(name = "we_from_date")
    private int weFromDate;

    @Column(name = "we_to_date")
    private int weToDate;

    @Column(name = "we_city")
    private String weCity;

    @Column(name = "we_description")
    private String weDescription;

    // Interests
    @Column(name = "user_interests")
    private String interests;


}
