// MessageControllerTest.java
package com.example.webapp.controller;

import com.example.webapp.model.Message;
import com.example.webapp.service.MessageService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(MessageController.class)
public class MessageControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MessageService messageService;

    @Test
    public void getAllMessages_ShouldReturnMessages() throws Exception {
        Message message1 = new Message(1L, "Hello");
        Message message2 = new Message(2L, "World");
        
        when(messageService.getAllMessages()).thenReturn(Arrays.asList(message1, message2));

        mockMvc.perform(get("/api/messages"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$[0].content").value("Hello"))
               .andExpect(jsonPath("$[1].content").value("World"));
    }

    @Test
    public void createMessage_ShouldReturnCreatedMessage() throws Exception {
        Message message = new Message(1L, "New message");
        
        when(messageService.createMessage("New message")).thenReturn(message);

        mockMvc.perform(post("/api/messages")
                .contentType("application/json")
                .content("{\"content\":\"New message\"}"))
               .andExpect(status().isCreated())
               .andExpect(jsonPath("$.content").value("New message"));
    }
}
