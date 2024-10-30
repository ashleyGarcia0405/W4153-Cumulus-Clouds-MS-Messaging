package com.cumulusclouds.w4153cumuluscloudsmsmessaging.controller;

import com.cumulusclouds.w4153cumuluscloudsmsmessaging.model.Message;
import com.cumulusclouds.w4153cumuluscloudsmsmessaging.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/messages")
public class MessageController {

  private final MessageService messageService;

  @Autowired
  public MessageController(MessageService messageService) {
    this.messageService = messageService;
  }

  @PostMapping
  public Message createMessage(@RequestBody Message message) {
    return messageService.saveMessage(message);
  }

  @GetMapping("/{id}")
  public Message getMessageById(@PathVariable UUID id) {
    return messageService.getMessageById(id);
  }

  @GetMapping("/user/{userId}")
  public List<Message> getMessagesByUser(@PathVariable UUID userId) {
    return messageService.getMessagesByUser(userId);
  }

  @DeleteMapping("/{id}")
  public void deleteMessage(@PathVariable UUID id) {
    messageService.deleteMessage(id);
  }
  
}
