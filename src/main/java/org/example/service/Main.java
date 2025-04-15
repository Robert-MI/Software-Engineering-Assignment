package org.example.service;

import org.example.api.NatsSubscriber;
import org.example.data.MessageRepository;

public class Main {
    public static void main(String[] args) {
        try {
            // PostgreSQL connection info
            String dbUrl = System.getenv().getOrDefault("DB_URL", "jdbc:postgresql://localhost:5432/mydb");
            String dbUser = System.getenv().getOrDefault("DB_USER", "robert");
            String dbPass = System.getenv().getOrDefault("DB_PASS", "12345678");

            // Initialize data + service layers
            MessageRepository repository = new MessageRepository(dbUrl, dbUser, dbPass);
            MessageService service = new MessageService(repository);

            // Start listening to NATS messages
            NatsSubscriber subscriber = new NatsSubscriber();
            subscriber.start(service);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
