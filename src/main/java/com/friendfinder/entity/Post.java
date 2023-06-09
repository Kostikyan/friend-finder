package com.friendfinder.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Table(name = "post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String description;
    private String imgName;
    private String musicFileName;
    private int likeCount;
    private int dislikeCount;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date postDatetime;

    @ManyToOne
    private User user;

}
