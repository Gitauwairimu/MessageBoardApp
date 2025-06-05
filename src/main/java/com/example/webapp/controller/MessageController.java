// src/main/java/com/example/webapp/controller/MessageController.java
package com.example.webapp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {
    
    @GetMapping("/messages")
    public String getMessages() {
        return "Hello from MessageController!";
    }
}
