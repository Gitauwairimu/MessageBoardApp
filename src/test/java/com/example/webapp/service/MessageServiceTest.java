// MessageServiceTest.java
package com.example.webapp.service;

import com.example.webapp.model.Message;
import com.example.webapp.repository.MessageRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MessageServiceTest {

    @Mock
    private MessageRepository messageRepository;

    @InjectMocks
    private MessageService messageService;

    @Test
    public void getAllMessages_ShouldReturnAllMessages() {
        // Arrange
        Message message1 = new Message(1L, "First message");
        Message message2 = new Message(2L, "Second message");
        when(messageRepository.findAll()).thenReturn(Arrays.asList(message1, message2));

        // Act
        List<Message> messages = messageService.getAllMessages();

        // Assert
        assertEquals(2, messages.size());
        assertEquals("First message", messages.get(0).getContent());
    }

    @Test
    public void createMessage_ShouldSaveMessage() {
        // Arrange
        Message newMessage = new Message(1L, "Test message");
        when(messageRepository.save(any(Message.class))).thenReturn(newMessage);

        // Act
        Message created = messageService.createMessage("Test message");

        // Assert
        assertEquals("Test message", created.getContent());
        verify(messageRepository, times(1)).save(any(Message.class));
    }
}
