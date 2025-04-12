package org.example;

import org.example.service.*;
import org.junit.jupiter.api.Test;
import java.sql.*;
import org.example.data.MessageRepository;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class MessageServiceTest {

    @Test
    public void testProcessMessageInsertsToDB() throws Exception {
        String url = "jdbc:postgresql://localhost:5432/mydb";
        String user = "robert";
        String password = "12345678";

        MessageRepository repository = new MessageRepository(url, user, password);
        MessageService service = new MessageService(repository);

        // Clean the table before test
        try (Connection conn = DriverManager.getConnection(url, user, password);
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate("DELETE FROM messages");
        }

        String testMessage = "Hello test World";
        service.processMessage(testMessage);

        // Verify the message is inserted
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM messages WHERE content = ?")) {
            stmt.setString(1, testMessage);

            try (ResultSet rs = stmt.executeQuery()) {
                assertTrue(rs.next(), "Message was not inserted");
            }
        }
    }
}
