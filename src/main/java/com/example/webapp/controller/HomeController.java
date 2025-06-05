// src/main/java/com/example/webapp/controller/HomeController.java
package com.example.webapp.controller;

import com.example.webapp.model.Message;
import com.example.webapp.repository.MessageRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    private final MessageRepository messageRepository;

    public HomeController(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @GetMapping("/")
    public String home(Model model) {
        List<Message> messages = messageRepository.findAll();
        model.addAttribute("messages", messages);
        return "index";
    }
}