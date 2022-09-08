package com.balaji.kafka.entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Order {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int orderId;
	@ManyToMany(cascade=CascadeType.ALL)
	@JoinTable(
	  name = "order_items", 
	  joinColumns = @JoinColumn(name = "order_id"), 
	  inverseJoinColumns = @JoinColumn(name = "product_id"))
	private List<Product> products;
	private double totalPrice;
	private LocalDate orderDate;
	private String deliveryAddress;
}
