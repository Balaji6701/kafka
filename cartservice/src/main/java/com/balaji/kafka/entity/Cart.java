package com.balaji.kafka.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Cart {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int cartId;
	@ManyToMany(cascade=CascadeType.ALL)
	@JoinTable(
	  name = "cart_items", 
	  joinColumns = @JoinColumn(name = "cart_id"), 
	  inverseJoinColumns = @JoinColumn(name = "product_id"))
	private List<Product> products;
}
