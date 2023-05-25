package com.friendfinder.entity;

import com.friendfinder.entity.types.FriendStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Table(name = "friend_request")
public class FriendRequest {

    @ManyToOne
    private User senderId;
    @ManyToOne
    private User receiverId;

    @Enumerated(value = EnumType.STRING)
    private FriendStatus status;
}
