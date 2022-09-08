package com.balaji.kafka.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.balaji.kafka.entity.ShippingDetails;
import com.balaji.kafka.services.ShippingService;

@RestController
@RequestMapping("/shipping")
public class ShippingController {
	
	@Autowired
	private ShippingService shippingService;
	
	@GetMapping("/{id}")
	public ShippingDetails getShippingDetails(@PathVariable("id") int shippingId) {
		return shippingService.getShippingDetailsById(shippingId);
	}
}
