package com.balaji.cosmos.entities;



import com.cts.approval.audit.Auditable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@Entity
public class Product extends Auditable<String> {

	private String productId;
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
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
