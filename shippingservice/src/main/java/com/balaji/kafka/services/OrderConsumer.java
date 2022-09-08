package com.balaji.kafka.services;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.balaji.kafka.entity.Order;
import com.balaji.kafka.entity.ShippingDetails;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class OrderConsumer {
	
	@Autowired
	private ShippingService shippingService;
	
	@KafkaListener(topics = "t-processed-order", concurrency = "3", groupId = "cg-shipping-processor")
	public void readOrder(Order order) {
		log.info("Order: {} received",order);
		saveShippingDetails(order);
	}
	
	private void saveShippingDetails(Order order) {
		Random random = new Random();
		ShippingDetails shippingDetails = new ShippingDetails();
		shippingDetails.setContactNumber(random.nextLong(9000000000l, 10000000000l));
		shippingDetails.setCustomerNumber(random.nextLong(9000000000l, 10000000000l));
		shippingDetails.setDeliveryAddress(order.getDeliveryAddress());
		shippingDetails.setEstimatedDeliveryDate(order.getOrderDate().plusDays(random.nextLong(3,10)));
		shippingDetails.setOrderDate(order.getOrderDate());
		shippingDetails.setOrderId(order.getOrderId());
		shippingService.saveShippingDetails(shippingDetails);
	}
}
