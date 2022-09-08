package com.balaji.kafka.entity;

import java.time.LocalDate;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
	
	private int orderId;
	private List<Product> products;
	private double totalPrice;
	private LocalDate orderDate;
	private String deliveryAddress;
}
