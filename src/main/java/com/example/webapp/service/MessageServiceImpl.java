// src/main/java/com/example/webapp/service/MessageServiceImpl.java
package com.example.webapp.service;

import com.example.webapp.model.Message;
import com.example.webapp.repository.MessageRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;

    public MessageServiceImpl(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Override
    public List<Message> getAllMessages() {
        return messageRepository.findAll();
    }

    @Override
    public Message createMessage(String content) {
        Message message = new Message(content);
        return messageRepository.save(message);
    }
}