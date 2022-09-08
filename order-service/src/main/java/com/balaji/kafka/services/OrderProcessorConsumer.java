package com.balaji.kafka.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.balaji.kafka.entity.Order;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class OrderProcessorConsumer {
	
	@Autowired
	private OrderService orderService;
	@Autowired
	private OrderPublisher orderPublisher;
	
	@KafkaListener(topics = "t-order", concurrency = "3", groupId = "cg-order-processor")
	public void readOrder(Order order) {
		Order savedOrder = orderService.saveOrder(order);
		log.info("order:{} received", savedOrder);
		orderPublisher.publishOrder(savedOrder);
	}
}
