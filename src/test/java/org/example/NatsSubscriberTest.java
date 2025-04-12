package org.example;

import org.example.api.NatsSubscriber;
import org.example.data.MessageRepository;
import org.example.service.MessageService;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class NatsSubscriberTest {

    @Test
    public void testSubscriberSetupDoesNotThrow() {
        assertDoesNotThrow(() -> {
            NatsSubscriber subscriber = new NatsSubscriber();

            // Fake repository that just prints instead of saving to DB
            MessageRepository fakeRepo = new MessageRepository("jdbc:postgresql://localhost:5432/postgres", "robert", "12345678") {
                @Override
                public void saveMessage(String content) {
                    System.out.println("Fake save: " + content);
                }
            };

            MessageService service = new MessageService(fakeRepo);

            Thread thread = new Thread(() -> subscriber.start(service));
            thread.start();

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
    }
}
