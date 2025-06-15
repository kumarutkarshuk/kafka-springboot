package app.vercel.utkarsh.kafka_springboot.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class MessageConsumer {

    @KafkaListener(topics = "my-topic", groupId = "group1")
    public void listenGroup1A(String message) {
        System.out.println("group1 consumer1: " + " message: " + message);
    }

    @KafkaListener(topics = "my-topic", groupId = "group1")
    public void listenGroup1B(String message) {
        System.out.println("group1 consumer2: " + " message: " + message);
    }

    @KafkaListener(topics = "my-topic", groupId = "group2")
    public void listenGroup2(String message) {
        System.out.println("group2: " + " message: " + message);
    }

}