package com.spring.project.service;


import com.spring.project.pojo.Message;

import java.util.List;

public interface MessageService {

    Message createMessage(Message message);

    List<Message> getUnreadMessagesByRecipientId(Long recipientId);

    String readMessage(String messageId);

    List<Message> getAllMessages();

    List<Message> getAllMessagesByUserId(Long userId);
}
