package com.balaji.kafka.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.balaji.kafka.entity.Order;

@Service
public class OrderPublisher {
	
	@Autowired
	private KafkaTemplate<String, Order> kafkaTemplate;

	public void publishOrder(Order order) {
		kafkaTemplate.send("t-order", order);
	}

}
