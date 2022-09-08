package com.balaji.kafka.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.balaji.kafka.entity.Order;
import com.balaji.kafka.services.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	@GetMapping("/{id}")
	public Order getOrder(@PathVariable("id")int id) {
		return orderService.getOrderById(id);
	}
}
