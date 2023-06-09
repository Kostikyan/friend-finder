package com.friendfinder.service;

import com.friendfinder.entity.Chat;

import java.util.List;
import java.util.Optional;

public interface ChatService {
    List<Chat> findAllByCurrentUserId(int currentUserId);

    List<Chat> findAllByAnotherUserId(int anotherUserId);

    Optional<Chat> findByCurrentUserIdAndAnotherUserId(int firstId, int secondId);

    Optional<Chat> findById(int id);

    void save(Chat chat);
}

