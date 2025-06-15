package app.vercel.utkarsh.kafka_springboot.topic;

import jakarta.annotation.PostConstruct;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.errors.TopicExistsException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.util.Collections;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

@Component
public class KafkaTopicCreator {
    @Value("${spring.kafka.bootstrap-servers}")
    private String serverUrl;

    @PostConstruct
    public void createTopic() {
        Properties config = new Properties();
        config.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, serverUrl);
        try (AdminClient admin = AdminClient.create(config)) {
            NewTopic topic = new NewTopic("my-topic", 2, (short) 1);
            try {
                admin.createTopics(Collections.singleton(topic)).all().get();
                System.out.println("Topic created.");
            } catch (ExecutionException e) {
                if (e.getCause() instanceof TopicExistsException) {
                    System.out.println("Topic already exists.");
                } else {
                    throw e;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
