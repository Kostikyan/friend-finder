package com.friendfinder.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "chat")
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    private User currentUser;

    @ManyToOne
    private User anotherUser;

    @OneToMany(mappedBy="chat")
    private List<Message> messages;
}
