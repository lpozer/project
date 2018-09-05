package com.spring.project.controller;

import com.spring.project.pojo.Authority;
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

import java.security.AccessControlException;
import java.util.List;

import static com.spring.project.utils.SessionUtils.getAuthenticatedUser;

@RestController
public class MessageController {

    @Autowired
    private MessageService messageService;

    @PostMapping(path = "/message")
    public Message getMessage(@RequestBody Message message) {
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

    @GetMapping(path = "message/audit")
    public List<Message> auditMessages() {
        if (!getAuthenticatedUser().getAuthorities().contains(Authority.AUDITOR)) {
            throw new AccessControlException("User does not have permission to perform this action");
        }
        return messageService.getAllMessages();
    }

    @GetMapping(path = "message/audit/{userId}")
    public List<Message> auditMessages(@PathVariable Long userId) {
        if (!getAuthenticatedUser().getAuthorities().contains(Authority.AUDITOR)) {
            throw new AccessControlException("User does not have permission to perform this action");
        }
        return messageService.getAllMessagesByUserId(userId);
    }
}
