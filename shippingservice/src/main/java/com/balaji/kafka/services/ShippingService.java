package com.balaji.kafka.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.balaji.kafka.entity.ShippingDetails;
import com.balaji.kafka.repository.ShippingRepository;

@Service
public class ShippingService {
	
	@Autowired
	private ShippingRepository shippingRepository;
	
	public void saveShippingDetails(ShippingDetails shippingDetails) {
		shippingRepository.save(shippingDetails);
	}
	
	public ShippingDetails getShippingDetailsById(int id) {
		return shippingRepository.findById(id).get();
	}
}
