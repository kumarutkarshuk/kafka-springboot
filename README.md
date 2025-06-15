## Kafka Architecture Overview

- Topic: my-topic
- Partitions: 2 (partition 0 and partition 1)
- Consumer Groups:
  - group1: 2 consumers (listenGroup1A, listenGroup1B)
  - group2: 1 consumer (listenGroup2)

## Prerequisites

Ensure you have the following installed on your machine:
- **IntelliJ IDEA** (or any Java-supporting IDE)
- **Java JDK** (or at least JRE) (if not bundled with your IDE)
- **Maven** (if not bundled with your IDE)
- **Docker**

## Steps to run the app
1. Start Zookeeper container and expose the port `2181`
```bash
docker run -p 2181:2181 zookeeper
```
2. Start Kafka container, expose the port `9092`, and set up environment variables
```bash
docker run -p 9092:9092 \
-e KAFKA_ZOOKEEPER_CONNECT=<PRIVATE_IP>:2181 \
-e KAFKA_ADVERTISED_LISTENERS=PLAINTEXT://<PRIVATE_IP>:9092 \
-e KAFKA_OFFSETS_TOPIC_REPLICATION_FACTOR=1 \
confluentinc/cp-kafka
```
3. Run the Spring Boot app

4. Send messages via the only producer on the API Endpoint: `POST /send`

    Query Parameters:
  
      | Name       | Type   | Required | Description          |
      |------------|--------|----------|----------------------|
      | partition  | int    | Yes      | 0 or 1               |
      | key        | string | Yes      | Message key          |
      | message    | string | Yes      | The actual message   |
  
    Response: Message sent: YOUR_MESSAGE

    **NOTE:** Spring Boot web app runs on the default port: 8080

  
