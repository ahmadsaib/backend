package com.Ahmad.Caterer.Service;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ConsumerService {

	private final Logger logger = LoggerFactory.getLogger(ProducerService.class);

	@KafkaListener(topics = "broker", groupId = "group_id")
	public void consume(String message) throws IOException {
		logger.info(String.format("#### -> Consumed message -> %s", message));
		System.out.println("#### -> Consumed message -> " + message);
	}
}
