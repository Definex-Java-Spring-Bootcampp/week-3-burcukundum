package com.patika.kredinbizdeservice.exceptions;

import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.springframework.beans.factory.annotation.Value;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.bson.Document;


import java.time.Duration;
import java.util.Collections;
import java.util.Properties;
import org.springframework.stereotype.Component;

@Component
public class KafkaToMongoDbConsumer {
    private final KafkaConsumer<String, String> consumer;
    private final MongoCollection<Document> collection;

    public KafkaToMongoDbConsumer(@Value("${kafka.topic}") String topicName,
                                  @Value("${mongodb.uri}") String mongoUri,
                                  @Value("${mongodb.database}") String databaseName,
                                  @Value("${mongodb.collection}") String collectionName) {

        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092"); // Kafka broker address
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "my-consumer-group");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());

        this.consumer = new KafkaConsumer<>(props);
        this.consumer.subscribe(Collections.singletonList(topicName));

        // MongoDB connection
        var mongoClient = MongoClients.create(mongoUri);
        MongoDatabase database = mongoClient.getDatabase(databaseName);
        this.collection = database.getCollection(collectionName);
    }

    public void consumeMessages() {
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
            records.forEach(record -> {
                String messageKey = record.key();
                String messageValue = record.value();
                System.out.println("Received message: Key = " + messageKey + ", Value = " + messageValue);

                // Insert the message into MongoDB
                Document document = new Document("key", messageKey)
                        .append("value", messageValue);
                collection.insertOne(document);
                System.out.println("Inserted into MongoDB");
            });
        }
    }

    public static void main(String[] args) {
        String topicName = "my-topic";
        String mongoUri = "mongodb://localhost:27017";
        String databaseName = "test";
        String collectionName = "logsColection";

        KafkaToMongoDbConsumer consumer = new KafkaToMongoDbConsumer(topicName, mongoUri, databaseName, collectionName);
        consumer.consumeMessages();
    }
}
