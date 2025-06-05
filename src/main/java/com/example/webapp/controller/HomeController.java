// src/main/java/com/example/webapp/controller/HomeController.java
package com.example.webapp.controller;

import com.example.webapp.model.Message;
import com.example.webapp.repository.MessageRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
        return "index";  // Renders src/main/resources/templates/index.html
    }

    @PostMapping("/add")
    public String handleSubmit(@RequestParam String text) {
        Message message = new Message();
        message.setText(text);  // assuming your entity uses 'text'
        messageRepository.save(message);
        return "redirect:/";
    }
}
