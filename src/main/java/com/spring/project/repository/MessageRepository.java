package com.spring.project.repository;

import com.spring.project.pojo.Message;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MessageRepository extends CrudRepository<Message, String> {

    Message findByMessageId(String messageId);

    List<Message> findAllByRecipientIdAndReadFalse(Long recipientId);

    List<Message> findAllByRecipientIdOrSenderId(Long recipientId, Long senderId);
}
