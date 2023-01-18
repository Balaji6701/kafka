package com.balaji.cosmos.kafka;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.balaji.cosmos.entities.Product;

@Service
public class ProductPublisher {

	@Autowired
	public KafkaTemplate<String, Product> kafkaTemplate;

	public void publishForApproval(Product product) {
		String name = SecurityContextHolder.getContext().getAuthentication().getName();
		product.setCreatedBy(name);
		product.setCreatedAt(LocalDateTime.now());
		Message<Product> message = MessageBuilder.withPayload(product)
				.setHeader(KafkaHeaders.TOPIC, "t-product-approval").build();
		kafkaTemplate.send(message);
	}
}
