package com.cts.approval.kafka;

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

	public void publishApprovedProduct(Product product) {
		String name = SecurityContextHolder.getContext().getAuthentication().getName();
		product.setLastModifiedBy(name);
		Message<Product> message = MessageBuilder.withPayload(product).setHeader(KafkaHeaders.TOPIC, "t-approved-products").build();
		kafkaTemplate.send(message);
	}
}
