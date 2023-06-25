package com.friendfinder.service.impl;

import com.friendfinder.entity.Chat;
import com.friendfinder.entity.Language;
import com.friendfinder.repository.ChatRepository;
import com.friendfinder.repository.LanguageRepository;
import com.friendfinder.service.ChatService;
import com.friendfinder.service.LanguageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService {
    private final ChatRepository chatRepository;

    @Override
    public List<Chat> findAllByCurrentUserId(int currentUserId) {
        return chatRepository.findAllByCurrentUserId(currentUserId);
    }

    @Override
    public List<Chat> findAllByAnotherUserId(int anotherUserId) {
        return chatRepository.findAllByAnotherUserId(anotherUserId);
    }

    @Override
    public Optional<Chat> findByCurrentUserIdAndAnotherUserId(int firstId, int secondId) {
        return chatRepository.findByCurrentUserIdAndAnotherUserId(firstId, secondId);
    }

    @Override
    public void save(Chat chat) {
        chatRepository.save(chat);
    }
}
