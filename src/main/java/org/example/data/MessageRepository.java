package org.example.data;

import java.sql.*;
import java.time.Instant;

public class MessageRepository {
    private final Connection connection;

    public MessageRepository(String url, String user, String password) throws SQLException {
        connection = DriverManager.getConnection(url, user, password);
        createTableIfNotExists();
    }

    private void createTableIfNotExists() {
        String sql = """
            CREATE TABLE IF NOT EXISTS messages (
                id SERIAL PRIMARY KEY,
                content TEXT NOT NULL,
                received_at TIMESTAMP NOT NULL
            )
        """;

        try (Statement stmt = connection.createStatement()) {
            stmt.execute(sql);
            System.out.println("Table 'messages' is ready.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveMessage(String content) {
        String sql = "INSERT INTO messages (content, received_at) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, content);
            stmt.setTimestamp(2, Timestamp.from(Instant.now()));
            stmt.executeUpdate();
            System.out.println("Message saved to DB.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
