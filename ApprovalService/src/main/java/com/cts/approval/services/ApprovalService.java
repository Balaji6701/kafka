package com.cts.approval.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriTemplate;

import com.balaji.cosmos.entities.Product;
import com.cts.approval.kafka.ProductPublisher;
import com.cts.approval.repositories.ProductRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ApprovalService {

	private ProductRepository productRepository;
	private ProductPublisher productPublisher;

	public void approveProduct(String id) {
		Product product = productRepository.findById(id).get();
		product.setApprovalStatus("Approved");
		productPublisher.publishApprovedProduct(product);
		productRepository.deleteById(id);
	}

	public void approveProductRest(String id) {
		RestTemplate restTemplate = new RestTemplateBuilder().basicAuthentication("user", "password")
				.rootUri(new UriTemplate("http://localhost:8080/products").toString()).build();
		Product product = productRepository.findById(id).get();
		product.setApprovalStatus("Approved");
		restTemplate.postForEntity("/approval", product, Product.class);
		productRepository.deleteById(id);
	}
	
	public void rejectProductRest(String id) {
		RestTemplate restTemplate = new RestTemplateBuilder().basicAuthentication("user", "password")
				.rootUri(new UriTemplate("http://localhost:8080/products").toString()).build();
		Product product = productRepository.findById(id).get();
		product.setApprovalStatus("Rejected");
		restTemplate.postForEntity("/approval", product, Product.class);
		productRepository.deleteById(id);
	}

	public List<Product> getAllProductsForApproval() {
		List<Product> products = new ArrayList<>();
		productRepository.findAll().forEach(products::add);
		return products;
	}

	public void rejectProduct(String id) {
		Product product = productRepository.findById(id).get();
		product.setApprovalStatus("Rejected");
		productPublisher.publishApprovedProduct(product);
		productRepository.deleteById(id);
	}

	public Product saveProduct(Product product) {
		return productRepository.save(product);
	}

}
