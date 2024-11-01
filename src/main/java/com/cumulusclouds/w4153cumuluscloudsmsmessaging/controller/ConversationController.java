package com.cumulusclouds.w4153cumuluscloudsmsmessaging.controller;

import com.cumulusclouds.w4153cumuluscloudsmsmessaging.model.Conversation;
import com.cumulusclouds.w4153cumuluscloudsmsmessaging.service.ConversationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/api/conversations")
public class ConversationController {

  private final ConversationService conversationService;

  @Autowired
  public ConversationController(ConversationService conversationService) {
    this.conversationService = conversationService;
  }

  @Operation(
          summary = "Create a new conversation",
          description = "Creates a new conversation with the provided details."
  )
  @ApiResponse(
          responseCode = "200",
          description = "Conversation created successfully",
          content = @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = Conversation.class)
          )
  )
  @ApiResponse(
          responseCode = "400",
          description = "Invalid conversation data provided"
  )
  @PostMapping("/")
  public EntityModel<Conversation> createConversation(@RequestBody Conversation conversation) {
    Conversation savedConversation = conversationService.saveConversation(conversation);
    Link selfLink = linkTo(methodOn(ConversationController.class).getConversationById(savedConversation.getConversationId())).withSelfRel();
    return EntityModel.of(savedConversation, selfLink);
  }

  @Operation(
          summary = "Retrieve a conversation by ID",
          description = "Fetches the conversation details based on the provided conversation ID."
  )
  @ApiResponse(
          responseCode = "200",
          description = "Conversation found successfully",
          content = @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = Conversation.class)
          )
  )
  @ApiResponse(
          responseCode = "404",
          description = "Conversation not found"
  )
  @Parameter(
          description = "ID of the conversation to retrieve",
          required = true
  )
  @GetMapping("/{id}")
  public EntityModel<Conversation> getConversationById(@PathVariable UUID id) {
    Conversation conversation = conversationService.getConversationById(id);
    Link selfLink = linkTo(methodOn(ConversationController.class).getConversationById(id)).withSelfRel();
    Link deleteLink = linkTo(methodOn(ConversationController.class).deleteConversation(id)).withRel("delete");
    return EntityModel.of(conversation, selfLink, deleteLink);
  }

  @Operation(
          summary = "Retrieve conversation between two users",
          description = "Fetches the conversation between two specified users based on their user IDs."
  )
  @ApiResponse(
          responseCode = "200",
          description = "Conversation retrieved successfully",
          content = @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = Conversation.class)
          )
  )
  @ApiResponse(
          responseCode = "404",
          description = "Conversation not found between the specified users"
  )
  @Parameter(
          description = "ID of the first user",
          required = true
  )
  @Parameter(
          description = "ID of the second user",
          required = true
  )
  @GetMapping("/between")
  public Optional<Conversation> getConversationBetweenUsers(@RequestParam UUID user1Id, @RequestParam UUID user2Id) {
    return conversationService.getConversationBetweenUsers(user1Id, user2Id);
  }

  @Operation(
          summary = "Retrieve conversations by user ID",
          description = "Fetches a list of all conversations associated with the specified user ID."
  )
  @ApiResponse(
          responseCode = "200",
          description = "Conversations retrieved successfully",
          content = @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = Conversation.class)
          )
  )
  @ApiResponse(
          responseCode = "404",
          description = "User not found or no conversations available"
  )
  @Parameter(
          description = "ID of the user to retrieve conversations for",
          required = true
  )
  @GetMapping("/user/{userId}")
  public EntityModel<List<Conversation>> getConversationsByUser(@PathVariable UUID userId) {
    List<Conversation> conversations = conversationService.getConversationsByUser(userId);
    Link selfLink = linkTo(methodOn(ConversationController.class).getConversationsByUser(userId)).withSelfRel();
    return EntityModel.of(conversations, selfLink);
  }

  @Operation(
          summary = "Delete a conversation",
          description = "Deletes the conversation with the specified conversation ID."
  )
  @ApiResponse(
          responseCode = "204",
          description = "Conversation deleted successfully"
  )
  @ApiResponse(
          responseCode = "404",
          description = "Conversation not found"
  )
  @Parameter(
          description = "ID of the conversation to delete",
          required = true
  )
  @DeleteMapping("/{id}")
  public UUID deleteConversation(@PathVariable UUID id) {
    conversationService.deleteConversation(id);
    return id;
  }
  
}

