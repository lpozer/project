package com.spring.project.service;

import com.spring.project.pojo.Message;
import com.spring.project.repository.MessageRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

@Lazy
@Service
public class MessageServiceImpl implements MessageService {

    private MessageRepository messageRepository;

    public MessageServiceImpl(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Override
    public Message createMessage(Message message) {
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

}
