package com.cumulusclouds.w4153cumuluscloudsmsmessaging.controller;

import com.cumulusclouds.w4153cumuluscloudsmsmessaging.model.Conversation;
import com.cumulusclouds.w4153cumuluscloudsmsmessaging.service.ConversationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/conversations")
public class ConversationController {

  private final ConversationService conversationService;

  @Autowired
  public ConversationController(ConversationService conversationService) {
    this.conversationService = conversationService;
  }

  @PostMapping
  public Conversation createConversation(@RequestBody Conversation conversation) {
    return conversationService.saveConversation(conversation);
  }

  @GetMapping("/{id}")
  public Conversation getConversationById(@PathVariable UUID id) {
    return conversationService.getConversationById(id);
  }

  @GetMapping("/between")
  public Optional<Conversation> getConversationBetweenUsers(@RequestParam UUID user1Id, @RequestParam UUID user2Id) {
    return conversationService.getConversationBetweenUsers(user1Id, user2Id);
  }

  @GetMapping("/user/{userId}")
  public List<Conversation> getConversationsByUser(@PathVariable UUID userId) {
    return conversationService.getConversationsByUser(userId);
  }

  @DeleteMapping("/{id}")
  public void deleteConversation(@PathVariable UUID id) {
    conversationService.deleteConversation(id);
  }
  
}

