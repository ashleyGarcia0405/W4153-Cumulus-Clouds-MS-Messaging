package com.cumulusclouds.w4153cumuluscloudsmsmessaging.controller;

import com.cumulusclouds.w4153cumuluscloudsmsmessaging.model.Attachment;
import com.cumulusclouds.w4153cumuluscloudsmsmessaging.service.AttachmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/attachments")
public class AttachmentController {

  private final AttachmentService attachmentService;

  @Autowired
  public AttachmentController(AttachmentService attachmentService) {
    this.attachmentService = attachmentService;
  }

  @Operation(
          summary = "Create a new attachment",
          description = "Creates a new attachment with the provided details."
  )
  @ApiResponse(
          responseCode = "200",
          description = "Attachment created successfully",
          content = @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = Attachment.class)
          )
  )
  @ApiResponse(
          responseCode = "400",
          description = "Invalid attachment data provided"
  )
  @PostMapping("/")
  public EntityModel<Attachment> createAttachment(@RequestBody Attachment attachment) {
    Attachment savedAttachment = attachmentService.saveAttachment(attachment);
    EntityModel<Attachment> resource = EntityModel.of(savedAttachment);
    resource.add(linkTo(methodOn(AttachmentController.class).getAttachmentById(savedAttachment.getAttachmentId())).withSelfRel());
    return resource;
  }

  @Operation(
          summary = "Retrieve an attachment by ID",
          description = "Fetches the attachment details based on the provided attachment ID."
  )
  @ApiResponse(
          responseCode = "200",
          description = "Attachment found successfully",
          content = @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = Attachment.class)
          )
  )
  @ApiResponse(
          responseCode = "404",
          description = "Attachment not found"
  )
  @Parameter(
          description = "ID of the attachment to retrieve",
          required = true
  )
  @GetMapping("/{id}")
  public EntityModel<Attachment> getAttachmentById(@PathVariable UUID id) {
    Attachment attachment = attachmentService.getAttachmentById(id);
    EntityModel<Attachment> resource = EntityModel.of(attachment);
    resource.add(linkTo(methodOn(AttachmentController.class).getAttachmentById(id)).withSelfRel());
    resource.add(linkTo(methodOn(AttachmentController.class).getAttachmentsByMessageId(attachment.getMessageId())).withRel("attachmentsByMessage"));
    return resource;
  }

  @Operation(
          summary = "Retrieve attachments by message ID",
          description = "Fetches a list of all attachments associated with the specified message ID."
  )
  @ApiResponse(
          responseCode = "200",
          description = "Attachments retrieved successfully",
          content = @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = Attachment.class)
          )
  )
  @ApiResponse(
          responseCode = "404",
          description = "Message not found"
  )
  @Parameter(
          description = "ID of the message to retrieve attachments for",
          required = true
  )
  @GetMapping("/message/{messageId}")
  public List<EntityModel<Attachment>> getAttachmentsByMessageId(
          @PathVariable UUID messageId) {
    List<Attachment> attachments = attachmentService.getAttachmentsByMessageId(messageId);
    return attachments.stream()
            .map(attachment -> {
                EntityModel<Attachment> resource = EntityModel.of(attachment);
                resource.add(linkTo(methodOn(AttachmentController.class).getAttachmentById(attachment.getAttachmentId())).withSelfRel());
                return resource;
            })
            .collect(Collectors.toList());
  }

  @Operation(
          summary = "Delete an attachment",
          description = "Deletes the attachment with the specified attachment ID."
  )
  @ApiResponse(
          responseCode = "204",
          description = "Attachment deleted successfully"
  )
  @ApiResponse(
          responseCode = "404",
          description = "Attachment not found"
  )
  @Parameter(
          description = "ID of the attachment to delete",
          required = true
  )
  @DeleteMapping("/{id}")
  public void deleteAttachment(@PathVariable UUID id) {
    attachmentService.deleteAttachment(id);
  }
  
}
