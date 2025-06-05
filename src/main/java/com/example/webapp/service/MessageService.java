// src/main/java/com/example/webapp/service/MessageService.java
package com.example.webapp.service;

import com.example.webapp.model.Message;
import java.util.List;

public interface MessageService {
    List<Message> getAllMessages();
    Message createMessage(String content);
}