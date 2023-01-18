package com.balaji.cosmos.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

import com.balaji.cosmos.entities.Product;
import com.balaji.cosmos.services.ProductService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ProductConsumer {
	
	private ProductService productService;
	
	@KafkaListener(topics = "t-approved-products", groupId = "approved-products-cg", concurrency = "3")
	public void consumeProduct(Message<Product> product) {
		Product payload = product.getPayload();
		payload.setId(null);
		productService.saveApprovedProduct(payload);
	}
}
