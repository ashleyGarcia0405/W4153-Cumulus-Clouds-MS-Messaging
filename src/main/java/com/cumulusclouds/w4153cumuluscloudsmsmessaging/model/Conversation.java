package com.cumulusclouds.w4153cumuluscloudsmsmessaging.model;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "conversation")
public class Conversation {

  @Id
  @GeneratedValue
  @Column(name = "conversation_id", updatable = false, nullable = false)
  private UUID conversationId;

  @Column(name = "user1_id", nullable = false)
  private UUID user1Id;

  @Column(name = "user2_id", nullable = false)
  private UUID user2Id;

  @Column(name = "last_message_id")
  private UUID lastMessageId;

  public UUID getConversationId() {
    return conversationId;
  }

  public void setConversationId(UUID conversationId) {
    this.conversationId = conversationId;
  }

  public UUID getUser1Id() {
    return user1Id;
  }

  public void setUser1Id(UUID user1Id) {
    this.user1Id = user1Id;
  }

  public UUID getUser2Id() {
    return user2Id;
  }

  public void setUser2Id(UUID user2Id) {
    this.user2Id = user2Id;
  }

  public UUID getLastMessageId() {
    return lastMessageId;
  }

  public void setLastMessageId(UUID lastMessageId) {
    this.lastMessageId = lastMessageId;
  }

}
