package com.cumulusclouds.w4153cumuluscloudsmsmessaging.service;

import com.cumulusclouds.w4153cumuluscloudsmsmessaging.model.Message;
import com.cumulusclouds.w4153cumuluscloudsmsmessaging.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class MessageService {

  private final MessageRepository messageRepository;

  @Autowired
  public MessageService(MessageRepository messageRepository) {
    this.messageRepository = messageRepository;
  }

  public Message saveMessage(Message message) {
    return messageRepository.save(message);
  }

  public List<Message> getMessagesByUser(UUID userId) {
    return messageRepository.findBySenderIdOrReceiverId(userId, userId);
  }

  public Message getMessageById(UUID messageId) {
    return messageRepository.findById(messageId).orElse(null);
  }

  public void deleteMessage(UUID messageId) {
    messageRepository.deleteById(messageId);
  }
  
}
