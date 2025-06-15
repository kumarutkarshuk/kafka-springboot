package app.vercel.utkarsh.kafka_springboot.controller;

import app.vercel.utkarsh.kafka_springboot.producer.MessageProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KafkaController {

    @Autowired
    private MessageProducer messageProducer;

    @PostMapping("/send")
    public String sendMessage(@RequestParam("partition") int partition,
                              @RequestParam("key") String key,
                              @RequestParam("message") String message) {
        messageProducer.sendMessage("my-topic", partition, key, message);
        return "Message sent: " + message;
    }

}
