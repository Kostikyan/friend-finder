package com.friendfinder.service;

import com.friendfinder.entity.Message;

import java.util.List;

public interface MessageService {
    List<Message> findAllBySenderId(int id);
    List<Message> findAllByReceiverId(int id);
    List<Message> findAllBySenderIdAndReceiverId(int firstId, int secondId);
    List<Message> findAllByChatId(int id);

    void save(Message chat);
}