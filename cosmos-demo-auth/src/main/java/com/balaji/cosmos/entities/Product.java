package com.balaji.cosmos.entities;

import org.springframework.data.annotation.Id;

import com.azure.spring.data.cosmos.core.mapping.Container;
import com.azure.spring.data.cosmos.core.mapping.GeneratedValue;
import com.azure.spring.data.cosmos.core.mapping.PartitionKey;
import com.balaji.cosmos.audit.Auditable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@Container(containerName = "product", ru = "3000", autoCreateContainer = true)
public class Product extends Auditable<String> {

	@PartitionKey
	private String productId;
	@Id
	@GeneratedValue
	private String id;
	private String productName;
	private double price;
	private String productCategory;
	private boolean active;
	private String approvalStatus;
	private String brand;

	public Product(String productId, String productName, double price, String productCategory, boolean active,
			String brand) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.price = price;
		this.productCategory = productCategory;
		this.active = active;
		this.brand = brand;
	}

}
