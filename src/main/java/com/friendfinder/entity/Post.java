package com.friendfinder.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Data
@Table(name = "post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String description;
    private String imgName;
    private String musicFileName;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date postDateline;

    @ManyToOne
    private User user;

}
