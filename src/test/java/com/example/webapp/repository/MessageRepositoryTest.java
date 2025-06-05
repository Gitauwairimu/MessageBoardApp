// MessageRepositoryTest.java
package com.example.webapp.repository;

import com.example.webapp.model.Message;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class MessageRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private MessageRepository messageRepository;

    @Test
    public void whenFindAll_thenReturnAllMessages() {
        // Given
        Message message1 = new Message("First message");
        Message message2 = new Message("Second message");
        entityManager.persist(message1);
        entityManager.persist(message2);
        entityManager.flush();

        // When
        List<Message> found = messageRepository.findAll();

        // Then
        assertThat(found).hasSize(2);
        assertThat(found.get(0).getContent()).isEqualTo("First message");
    }

    @Test
    public void whenSave_thenPersistMessage() {
        // Given
        Message newMessage = new Message("Test message");

        // When
        Message saved = messageRepository.save(newMessage);

        // Then
        assertThat(saved).isNotNull();
        assertThat(saved.getId()).isNotNull();
        assertThat(saved.getContent()).isEqualTo("Test message");
    }
}
