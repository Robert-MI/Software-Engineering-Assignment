package org.example.service;

import org.example.data.MessageRepository;

public class MessageService {
    private final MessageRepository repository;

    public MessageService(MessageRepository repository) {
        this.repository = repository;
    }

    public void processMessage(String content) {
        if (content == null || content.trim().isEmpty()) {
            System.out.println("Invalid message.");
            return;
        }

        System.out.println("Valid message received: " + content);

        repository.saveMessage(content);
    }
}
