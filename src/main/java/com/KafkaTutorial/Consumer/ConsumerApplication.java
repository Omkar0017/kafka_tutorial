package com.KafkaTutorial.Consumer;

import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;

@SpringBootApplication
public class ConsumerApplication {
	private static final Logger LOGGER = LoggerFactory.getLogger(ConsumerApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(ConsumerApplication.class, args);
	}


	@KafkaListener(topics = "${kafka.topic_name}",groupId = "${group.id}")
	public void listener(String message){
		LOGGER.info("Inside Listener");
		LOGGER.info("Received message : {}", message);

	}


}
