package com.balaji.kafka.entity;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
	
	private int id;
	private String productName;
	private double price;
	private int quantity;
	private List<Order> orders;
}
