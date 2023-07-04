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
    private final MailService mailService;

    @Override
    public void save(FriendRequest friendRequest) {
        if (findBySenderIdAndReceiverId(friendRequest.getSender().getId(), friendRequest.getReceiver().getId()) == null
                && findBySenderIdAndReceiverId(friendRequest.getReceiver().getId(), friendRequest.getSender().getId()) == null)
            mailService.sendMail(friendRequest.getReceiver().getEmail(), "You have a new friend request", "Hi, "
                    + friendRequest.getReceiver().getName() + ". You have an friend request from " +
                    friendRequest.getSender().getName() + " " + friendRequest.getSender().getSurname());
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
        if (bySenderIdAndReceiverId.isPresent()){
            FriendRequest friendRequest = bySenderIdAndReceiverId.get();
            mailService.sendMail(friendRequest.getSender().getEmail(), "Your friend request is accepted",
                    "Hi, " + friendRequest.getSender().getName() +
                    ". " + friendRequest.getReceiver().getName() + " accepted your request.");
        }
        return bySenderIdAndReceiverId.orElse(null);
    }

    @Override
    public void delete(FriendRequest friendRequest) {
        friendRequestRepository.delete(friendRequest);
    }

    @Override
    public List<User> findFriendsByUserId(int userId) {
        List<FriendRequest> all = friendRequestRepository.findAll();
        List<User> result = new ArrayList<>();
        for (FriendRequest friendRequest : all) {
            if (friendRequest.getSender().getId() == userId && friendRequest.getStatus() == FriendStatus.ACCEPTED) {
                result.add(friendRequest.getReceiver());
            }
            if (friendRequest.getReceiver().getId() == userId && friendRequest.getStatus() == FriendStatus.ACCEPTED) {
                result.add(friendRequest.getSender());
            }
        }
        return result;
    }

    @Override
    public void changeStatus(FriendRequest friendRequest) {
        friendRequest.setStatus(FriendStatus.ACCEPTED);
        friendRequestRepository.save(friendRequest);
    }

    @Override
    public int findFriendsByUserIdCount(int id) {
        List<User> friendsByUserId = findFriendsByUserId(id);
        return friendsByUserId.size();
    }

}
