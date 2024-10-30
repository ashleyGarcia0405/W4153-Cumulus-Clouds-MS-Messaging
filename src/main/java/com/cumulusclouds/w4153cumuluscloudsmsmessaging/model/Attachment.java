package com.cumulusclouds.w4153cumuluscloudsmsmessaging.model;

import jakarta.persistence.*;
import java.util.UUID;
import java.time.Instant;

@Entity
@Table(name = "attachment")
public class Attachment {

  @Id
  @GeneratedValue
  @Column(name = "attachment_id", updatable = false, nullable = false)
  private UUID attachmentId;

  @Column(name = "message_id", nullable = false)
  private UUID messageId;

  @Column(name = "file_url", nullable = false)
  private String fileUrl;

  @Column(name = "file_type", nullable = false)
  private String fileType;

  @Column(name = "uploaded_at", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
  private Instant uploadedAt;

  public UUID getAttachmentId() {
    return attachmentId;
  }

  public void setAttachmentId(UUID attachmentId) {
    this.attachmentId = attachmentId;
  }

  public UUID getMessageId() {
    return messageId;
  }

  public void setMessageId(UUID messageId) {
    this.messageId = messageId;
  }

  public String getFileUrl() {
    return fileUrl;
  }

  public void setFileUrl(String fileUrl) {
    this.fileUrl = fileUrl;
  }

  public String getFileType() {
    return fileType;
  }

  public void setFileType(String fileType) {
    this.fileType = fileType;
  }

  public Instant getUploadedAt() {
    return uploadedAt;
  }

  public void setUploadedAt(Instant uploadedAt) {
    this.uploadedAt = uploadedAt;
  }

}
