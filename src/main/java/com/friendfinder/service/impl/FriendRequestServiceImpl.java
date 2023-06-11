package com.friendfinder.service.impl;

import com.friendfinder.entity.FriendRequest;
import com.friendfinder.entity.User;
import com.friendfinder.entity.types.FriendStatus;
import com.friendfinder.repository.FriendRequestRepository;
import com.friendfinder.service.FriendRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FriendRequestServiceImpl implements FriendRequestService {
    private final FriendRequestRepository friendRequestRepository;

    @Override
    public void save(FriendRequest friendRequest) {
        friendRequestRepository.save(friendRequest);
    }

    @Override
    public List<User> findSenderByReceiverId(int receiverId) {
        List<FriendRequest> frList = friendRequestRepository.findByReceiverId(receiverId);
        List<User> users = new ArrayList<>();
        for (FriendRequest friendRequest : frList) {
            if (friendRequest.getStatus() == FriendStatus.PENDING) {
                users.add(friendRequest.getSender());
            }
        }
        return users;
    }

    @Override
    public FriendRequest findBySenderIdAndReceiverId(int senderId, int receiverId) {
        Optional<FriendRequest> bySenderIdAndReceiverId = friendRequestRepository.findBySenderIdAndReceiverId(senderId, receiverId);
        return bySenderIdAndReceiverId.orElse(null);
    }

}
