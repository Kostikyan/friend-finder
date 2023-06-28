package com.friendfinder.service;

import com.friendfinder.entity.FriendRequest;
import com.friendfinder.entity.User;

import java.util.List;

public interface FriendRequestService {
    void save(FriendRequest friendRequest);

    List<User> findSenderByReceiverId(int receiverId);

    FriendRequest findBySenderIdAndReceiverId(int senderId, int receiverId);

    void delete(FriendRequest friendRequest);

    List<User> findFriendsByUserId(int userId);

    void changeStatus(FriendRequest friendRequest);

    int findFriendsByUserIdCount(int id);
}
