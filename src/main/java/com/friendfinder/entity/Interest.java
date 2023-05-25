package com.friendfinder.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "interests")
public class Interest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String interest;
}
