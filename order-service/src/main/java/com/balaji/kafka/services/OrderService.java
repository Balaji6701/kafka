package com.balaji.kafka.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.balaji.kafka.entity.Order;
import com.balaji.kafka.repository.OrderRepository;

@Service
public class OrderService {
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Transactional
	public Order saveOrder(Order order) {
		return orderRepository.save(order);
	}
	
	@Transactional
	public Order getOrderById(int id) {
		return orderRepository.findById(id).get();
	}
}
