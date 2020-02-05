package com.example.kafkademo.streams;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Bean;


@EnableBinding(Bindings.class)
@SpringBootApplication
public class KafkaDemoStreamApplication {

	public static void main(String[] args) {
		final SpringApplication application = new SpringApplication(KafkaDemoStreamApplication.class);
		// we don't need web/rest interface in this app
		// application.setWebApplicationType(WebApplicationType.NONE);
		// ^ for cf push, yes we do w/o changing the healthcheck...
		application.run(args);
	}

	@Value("${topic.name}-filtered")
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
