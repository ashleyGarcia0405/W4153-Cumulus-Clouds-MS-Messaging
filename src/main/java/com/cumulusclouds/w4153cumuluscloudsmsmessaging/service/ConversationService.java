package com.cumulusclouds.w4153cumuluscloudsmsmessaging.service;

import com.cumulusclouds.w4153cumuluscloudsmsmessaging.model.Conversation;
import com.cumulusclouds.w4153cumuluscloudsmsmessaging.repository.ConversationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ConversationService {

  private final ConversationRepository conversationRepository;

  @Autowired
  public ConversationService(ConversationRepository conversationRepository) {
    this.conversationRepository = conversationRepository;
  }

  public Conversation saveConversation(Conversation conversation) {
    return conversationRepository.save(conversation);
  }

  public Optional<Conversation> getConversationBetweenUsers(UUID user1Id, UUID user2Id) {
    return conversationRepository.findByUser1IdAndUser2Id(user1Id, user2Id);
  }

  public List<Conversation> getConversationsByUser(UUID userId) {
    return conversationRepository.findByUser1IdOrUser2Id(userId, userId);
  }

  public Conversation getConversationById(UUID conversationId) {
    return conversationRepository.findById(conversationId).orElse(null);
  }

  public void deleteConversation(UUID conversationId) {
    conversationRepository.deleteById(conversationId);
  }
  
}
