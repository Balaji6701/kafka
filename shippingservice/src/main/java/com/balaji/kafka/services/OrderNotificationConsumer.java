package com.balaji.kafka.services;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.balaji.kafka.entity.Order;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class OrderNotificationConsumer {
	
	@KafkaListener(topics = "t-processed-order", concurrency = "3", groupId = "cg-notification")
	public void readOrder(Order order) {
		log.info("Order: {} received, Notification sent",order);
	}
}
