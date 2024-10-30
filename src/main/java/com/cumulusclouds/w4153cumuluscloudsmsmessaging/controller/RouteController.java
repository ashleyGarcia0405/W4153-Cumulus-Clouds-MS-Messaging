package com.cumulusclouds.w4153cumuluscloudsmsmessaging.controller;

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

    @PostMapping("/send")
    public Message sendMessage(@RequestParam Long senderId, @RequestParam Long receiverId, @RequestParam String content) {
        return messageService.sendMessage(senderId, receiverId, content);
    }

    @GetMapping("/{userId}")
    public List<Message> getMessagesForUser(@PathVariable Long userId) {
        return messageService.getMessagesForUser(userId);
    }
}
