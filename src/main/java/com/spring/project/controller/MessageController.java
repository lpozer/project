package com.spring.project.controller;

import com.spring.project.pojo.Message;
import com.spring.project.pojo.User;
import com.spring.project.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
public class MessageController {

    @Autowired
    private MessageService messageService;

    @PostMapping(path = "/message")
    public Message getMessage(@RequestBody Message message) {
        message.setSenderId(getAuthenticatedUser().getUserId());
        message.setMessageId(generateMessageId(message.getText()));
        message.setRead(false);
        message.setSentTime(System.currentTimeMillis());
        return messageService.createMessage(message);
    }

    @GetMapping(path = "/message/unread")
    public List<Message> getAllUnreadMessages() {
        return messageService.getUnreadMessagesByRecipientId(getAuthenticatedUser().getUserId());
    }

    @GetMapping(path = "message/read/{messageId}")
    public String readMessage(@PathVariable String messageId) {
        return messageService.readMessage(messageId);
    }

    private User getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (User) authentication.getPrincipal();
    }

    private String generateMessageId(String messageText) {
        Long time = System.currentTimeMillis();
        String concatenated = messageText + time;
        return String.valueOf(concatenated.hashCode());
    }
}
