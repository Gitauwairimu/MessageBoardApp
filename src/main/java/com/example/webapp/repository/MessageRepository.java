// src/main/java/com/example/webapp/repository/MessageRepository.java
package com.example.webapp.repository;

import com.example.webapp.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long> {
}