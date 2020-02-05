package com.example.kafkademo.pubsub;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class KafkaDemoPubSubApplication {

	public static void main(String[] args) {
		SpringApplication.run(KafkaDemoPubSubApplication.class, args);
	}

	@Value("${topic.name}")
	private String topicName;

	@Value("${topic.partitions-num}")
	private Integer partitions;

	@Value("${topic.replication-factor}")
	private short replicationFactor;

	@Bean
	NewTopic newTopic() {
		return new NewTopic(topicName, partitions, replicationFactor);
	}
}
