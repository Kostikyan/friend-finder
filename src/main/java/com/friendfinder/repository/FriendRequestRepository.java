package com.friendfinder.repository;

import com.friendfinder.entity.FriendRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FriendRequestRepository extends JpaRepository<FriendRequest, Integer> {
    List<FriendRequest> findByReceiverId (int receiverId);

    Optional<FriendRequest> findBySenderIdAndReceiverId(int senderId,int receiverId);
}
