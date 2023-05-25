package com.friendfinder.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "commentary")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Post post;
    private String commentaryText;
}
