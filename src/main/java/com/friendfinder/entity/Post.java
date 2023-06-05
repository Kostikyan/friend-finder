package com.friendfinder.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String description;
    private String imgName;
    private String musicFileName;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date postDatetime;

    @ManyToOne
    private PostLike postLikes;


    @ManyToOne
    private User user;

}
