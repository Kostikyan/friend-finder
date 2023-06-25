package com.friendfinder.service.impl;

import com.friendfinder.entity.Message;
import com.friendfinder.repository.MessageRepository;
import com.friendfinder.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {
    private final MessageRepository messageRepository;

    @Override
    public List<Message> findAllBySenderId(int id) {
        return messageRepository.findAllBySender_Id(id);
    }

    @Override
    public List<Message> findAllByReceiverId(int id) {
        return messageRepository.findAllByReceiver_Id(id);
    }

    @Override
    public List<Message> findAllBySenderIdAndReceiverId(int firstId, int secondId) {
        return messageRepository.findAllBySender_IdAndReceiver_Id(firstId, secondId);
    }

    @Override
    public List<Message> findAllByChatId(int id) {
        return messageRepository.findAllByChatId(id);
    }

    @Override
    public void save(Message message) {
        messageRepository.save(message);
    }
}
