package com.friendfinder.service;

import com.friendfinder.entity.Chat;
import com.friendfinder.entity.Language;

import java.util.List;
import java.util.Optional;

public interface ChatService {
    List<Chat> findAllByCurrentUserId(int currentUserId);
    List<Chat> findAllByAnotherUserId(int anotherUserId);
    Optional<Chat> findByCurrentUserIdAndAnotherUserId(int firstId, int secondId);

    void save(Chat chat);
}
