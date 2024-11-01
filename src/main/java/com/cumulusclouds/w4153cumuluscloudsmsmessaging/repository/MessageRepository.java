package com.cumulusclouds.w4153cumuluscloudsmsmessaging.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

import com.cumulusclouds.w4153cumuluscloudsmsmessaging.model.Message;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, UUID> {
    List<Message> findBySenderIdOrReceiverId(UUID senderId, UUID receiverId);
}