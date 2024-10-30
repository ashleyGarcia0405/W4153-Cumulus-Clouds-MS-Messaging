package com.cumulusclouds.w4153cumuluscloudsmsmessaging.controller;

import com.cumulusclouds.w4153cumuluscloudsmsmessaging.model.Attachment;
import com.cumulusclouds.w4153cumuluscloudsmsmessaging.service.AttachmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/attachments")
public class AttachmentController {

  private final AttachmentService attachmentService;

  @Autowired
  public AttachmentController(AttachmentService attachmentService) {
    this.attachmentService = attachmentService;
  }

  @PostMapping
  public Attachment createAttachment(@RequestBody Attachment attachment) {
    return attachmentService.saveAttachment(attachment);
   }

  @GetMapping("/{id}")
  public Attachment getAttachmentById(@PathVariable UUID id) {
    return attachmentService.getAttachmentById(id);
  }

  @GetMapping("/message/{messageId}")
  public List<Attachment> getAttachmentsByMessageId(@PathVariable UUID messageId) {
    return attachmentService.getAttachmentsByMessageId(messageId);
  }

  @DeleteMapping("/{id}")
  public void deleteAttachment(@PathVariable UUID id) {
    attachmentService.deleteAttachment(id);
  }
  
}
