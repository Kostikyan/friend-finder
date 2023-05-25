package com.friendfinder.entity;

import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Table(name = "user_friends")
public class Friend {

    @ManyToOne
    private User userId;
    @ManyToOne
    private User friendId;
}
