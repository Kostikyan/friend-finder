package com.friendfinder.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Language {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String language;
    @ManyToOne
    private User user;
}
