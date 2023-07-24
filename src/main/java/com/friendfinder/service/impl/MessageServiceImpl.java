package com.friendfinder.service.impl;

import com.friendfinder.entity.Message;
import com.friendfinder.repository.MessageRepository;
import com.friendfinder.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {
    private final MessageRepository messageRepository;

    @Override
    public void save(Message message) {
        messageRepository.save(message);
    }
}
