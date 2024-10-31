package com.cumulusclouds.w4153cumuluscloudsmsmessaging.controller;

import com.cumulusclouds.w4153cumuluscloudsmsmessaging.model.Message;
import com.cumulusclouds.w4153cumuluscloudsmsmessaging.service.MessageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/messages")
public class MessageController {

  private final MessageService messageService;

  @Autowired
  public MessageController(MessageService messageService) {
    this.messageService = messageService;
  }

  @Operation(
          summary = "Create a new message",
          description = "Creates a new message with the provided details."
  )
  @ApiResponse(
          responseCode = "200",
          description = "Message created successfully",
          content = @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = Message.class)
          )
  )
  @ApiResponse(
          responseCode = "400",
          description = "Invalid message data provided"
  )
  @PostMapping("/")
  public ResponseEntity<Message> createMessage(@RequestBody Message message) {
    Message createdMessage = messageService.saveMessage(message);

    createdMessage.add(linkTo(methodOn(MessageController.class).getMessageById(createdMessage.getMessageId())).withSelfRel());
    createdMessage.add(linkTo(methodOn(MessageController.class).deleteMessage(createdMessage.getMessageId())).withRel("delete"));
    
    return ResponseEntity.status(HttpStatus.CREATED).body(createdMessage);
  }

  @Operation(
          summary = "Retrieve a message by ID",
          description = "Fetches the message details based on the provided message ID."
  )
  @ApiResponse(
          responseCode = "200",
          description = "Message found successfully",
          content = @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = Message.class)
          )
  )
  @ApiResponse(
          responseCode = "404",
          description = "Message not found"
  )
  @Parameter(
          description = "ID of the message to retrieve",
          required = true
  )
  @GetMapping("/{id}")
  public Message getMessageById(@PathVariable UUID id) {
    return messageService.getMessageById(id);
  }

  @Operation(
          summary = "Retrieve messages by user ID",
          description = "Fetches a list of all messages associated with the specified user ID."
  )
  @ApiResponse(
          responseCode = "200",
          description = "Messages retrieved successfully",
          content = @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = Message.class)
          )
  )
  @ApiResponse(
          responseCode = "404",
          description = "User not found or no messages available"
  )
  @Parameter(
          description = "ID of the user to retrieve messages for",
          required = true
  )
  @GetMapping("/user/{userId}")
  public List<Message> getMessagesByUser(@PathVariable UUID userId) {
    return messageService.getMessagesByUser(userId);
  }

  @Operation(
          summary = "Delete a message",
          description = "Deletes the message with the specified message ID."
  )
  @ApiResponse(
          responseCode = "204",
          description = "Message deleted successfully"
  )
  @ApiResponse(
          responseCode = "404",
          description = "Message not found"
  )
  @Parameter(
          description = "ID of the message to delete",
          required = true
  )
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteMessage(@PathVariable UUID id) {
    messageService.deleteMessage(id);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }
  
}
