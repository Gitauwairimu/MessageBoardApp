package com.example.webapp.controller;

import com.example.webapp.model.Message;
import com.example.webapp.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class HomeController {

    @Autowired
    private MessageRepository messageRepository;

    @GetMapping("/")
    public String home(Model model) {
        List<Message> sortedMessages = messageRepository.findAll().stream()
                .sorted(Comparator.comparing(Message::getCreatedAt).reversed())
                .collect(Collectors.toList());
        model.addAttribute("messages", sortedMessages);
        return "index";
    }

    @PostMapping("/add")
    public String addMessage(@RequestParam("text") String text) {
        if (!text.isBlank()) {
            messageRepository.save(new Message(text));
        }
        return "redirect:/";
    }
}
