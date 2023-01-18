package com.cts.approval.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

import com.balaji.cosmos.entities.Product;
import com.cts.approval.services.ApprovalService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ProductConsumer {
	
	private ApprovalService approvalService;
	
	@KafkaListener(topics = "t-product-approval", groupId = "approval-cg", concurrency = "3")
	public void consumeProduct(Message<Product> product) {
		Product savedProduct = approvalService.saveProduct(product.getPayload());
	}
}
