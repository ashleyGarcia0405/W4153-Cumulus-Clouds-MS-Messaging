package com.cumulusclouds.w4153cumuluscloudsmsmessaging.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.cumulusclouds.w4153cumuluscloudsmsmessaging.model.Message;
import com.cumulusclouds.w4153cumuluscloudsmsmessaging.service.MessageService;

import java.util.List;

@RestController
@RequestMapping("/api/messages")
public class RouteController {

    @Autowired
    private MessageService messageService;

    @Operation(
            summary = "Send a message",
            description = "Sends a message from a sender to a receiver with the specified content."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Message sent successfully",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = Message.class)
            )
    )
    @ApiResponse(
            responseCode = "400",
            description = "Invalid request parameters"
    )
    @Parameter(
            description = "ID of the message sender",
            required = true
    )
    @Parameter(
            description = "ID of the message receiver",
            required = true
    )
    @Parameter(
            description = "Content of the message",
            required = true
    )
    @PostMapping("/send")
    public Message sendMessage(@RequestParam Long senderId, @RequestParam Long receiverId, @RequestParam String content) {
        return messageService.sendMessage(senderId, receiverId, content);
    }

    @Operation(
            summary = "Retrieve messages for a user",
            description = "Fetches a list of all messages associated with the specified user ID."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Successfully retrieved messages",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = Message.class)
            )
    )
    @ApiResponse(
            responseCode = "404",
            description = "User not found"
    )
    @Parameter(
            description = "ID of the user to retrieve messages for",
            required = true
    )
    @GetMapping("/{userId}")
    public List<Message> getMessagesForUser(@PathVariable Long userId) {
        return messageService.getMessagesForUser(userId);
    }
}
