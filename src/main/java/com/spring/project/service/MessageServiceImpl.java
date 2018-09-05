package com.spring.project.service;

import com.spring.project.pojo.Message;
import com.spring.project.repository.MessageRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static com.spring.project.utils.SessionUtils.getAuthenticatedUser;

@Lazy
@Service
public class MessageServiceImpl implements MessageService {

    private MessageRepository messageRepository;

    public MessageServiceImpl(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Override
    public Message createMessage(Message message) {
        message.setSenderId(getAuthenticatedUser().getUserId());
        message.setMessageId(generateMessageId(message.getText()));
        message.setRead(false);
        message.setSentTime(System.currentTimeMillis());
        return messageRepository.save(message);
    }

    @Override
    public List<Message> getUnreadMessagesByRecipientId(Long recipientId) {
        return messageRepository.findAllByRecipientIdAndReadFalse(recipientId);
    }

    @Override
    public String readMessage(String messageId) {
        Message message = messageRepository.findByMessageId(messageId);
        message.setRead(true);
        messageRepository.save(message);
        return message.getText();
    }

    @Override
    public List<Message> getAllMessages() {
        return newArrayList(messageRepository.findAll());
    }

    @Override
    public List<Message> getAllMessagesByUserId(Long userId) {
        return messageRepository.findAllByRecipientIdOrSenderId(userId, userId);
    }

    private String generateMessageId(String messageText) {
        Long time = System.currentTimeMillis();
        String concatenated = messageText + time;
        return String.valueOf(concatenated.hashCode());
    }
}
