package com.cumulusclouds.w4153cumuluscloudsmsmessaging.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;
import java.util.List;

import com.cumulusclouds.w4153cumuluscloudsmsmessaging.model.Attachment;

@Repository
public interface AttachmentRepository extends JpaRepository<Attachment, UUID> {
    List<Attachment> findByMessageId(UUID messageId);
}
