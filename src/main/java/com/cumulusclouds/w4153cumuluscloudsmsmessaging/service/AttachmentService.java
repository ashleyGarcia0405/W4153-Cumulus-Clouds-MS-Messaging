package com.cumulusclouds.w4153cumuluscloudsmsmessaging.service;

import com.cumulusclouds.w4153cumuluscloudsmsmessaging.model.Attachment;
import com.cumulusclouds.w4153cumuluscloudsmsmessaging.repository.AttachmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AttachmentService {

  private final AttachmentRepository attachmentRepository;

  @Autowired
  public AttachmentService(AttachmentRepository attachmentRepository) {
    this.attachmentRepository = attachmentRepository;
  }

  public Attachment saveAttachment(Attachment attachment) {
    return attachmentRepository.save(attachment);
  }

  public List<Attachment> getAttachmentsByMessageId(UUID messageId) {
    return attachmentRepository.findByMessageId(messageId);
  }

  public Attachment getAttachmentById(UUID attachmentId) {
    return attachmentRepository.findById(attachmentId).orElse(null);
  }

  public void deleteAttachment(UUID attachmentId) {
    attachmentRepository.deleteById(attachmentId);
  }
  
}

