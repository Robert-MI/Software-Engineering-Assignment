package org.example;
import org.example.data.MessageRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class MessageRepositoryTest {

    @Test
    public void testSaveMessageDoesNotThrow() {
        assertDoesNotThrow(() -> {
            MessageRepository repo = new MessageRepository(
                    "jdbc:postgresql://localhost:5432/postgres",
                    "robert",
                    "12345678"
            );
            repo.saveMessage("Simple test message");
        });
    }
}
