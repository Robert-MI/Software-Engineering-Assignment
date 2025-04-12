package org.example.api;

import io.nats.client.Connection;
import io.nats.client.Dispatcher;
import io.nats.client.Nats;
import org.example.service.MessageService;

import java.nio.charset.StandardCharsets;

public class NatsSubscriber {
    private static final String SUBJECT = "messages";

    public void start(MessageService service) {
        try (Connection nc = Nats.connect("demo.nats.io")) {
            System.out.println("Connected to NATS");

            Dispatcher dispatcher = nc.createDispatcher((msg) -> {
                String content = new String(msg.getData(), StandardCharsets.UTF_8);
                System.out.println("Received message: " + content);
                service.processMessage(content);
            });

            dispatcher.subscribe(SUBJECT);
            System.out.println("Subscribed to subject: " + SUBJECT);

            while (true) {
                //To wait for messages
                Thread.sleep(1000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
