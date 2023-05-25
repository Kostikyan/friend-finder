package com.friendfinder.entity;

import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Table(name = "user_friend")
public class Friend {

    @ManyToOne
    private User userId;
    @ManyToOne
    private User friendId;
}
