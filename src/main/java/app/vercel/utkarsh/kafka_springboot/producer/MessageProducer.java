package app.vercel.utkarsh.kafka_springboot.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class MessageProducer {
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(String topic, int partition, String key, String message) {
        kafkaTemplate.send(topic, partition, key, message);
    }
}
