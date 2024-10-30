package com.cumulusclouds.w4153cumuluscloudsmsmessaging.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;
import java.util.Optional;
import java.util.List;

import com.cumulusclouds.w4153cumuluscloudsmsmessaging.model.Conversation;

@Repository
public interface ConversationRepository extends JpaRepository<Conversation, UUID> {
  Optional<Conversation> findByUser1IdAndUser2Id(UUID user1Id, UUID user2Id);
  List<Conversation> findByUser1IdOrUser2Id(UUID userId1, UUID userId2);
}
