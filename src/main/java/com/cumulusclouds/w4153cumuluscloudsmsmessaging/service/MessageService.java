package com.cumulusclouds.w4153cumuluscloudsmsmessaging.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cumulusclouds.w4153cumuluscloudsmsmessaging.model.Message;
import com.cumulusclouds.w4153cumuluscloudsmsmessaging.repository.MessageRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    public Message sendMessage(Long senderId, Long receiverId, String content) {
        Message message = new Message();
        message.setSenderId(senderId);
        message.setReceiverId(receiverId);
        message.setContent(content);
        message.setTimestamp(LocalDateTime.now());
        return messageRepository.save(message);
    }

    public List<Message> getMessagesForUser(Long userId) {
        return messageRepository.findBySenderIdOrReceiverId(userId, userId);
    }
}
