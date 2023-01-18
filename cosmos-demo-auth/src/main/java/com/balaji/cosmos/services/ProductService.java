package com.balaji.cosmos.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriTemplate;

import com.azure.cosmos.models.PartitionKey;
import com.balaji.cosmos.entities.ApprovalResponse;
import com.balaji.cosmos.entities.Product;
import com.balaji.cosmos.exceptions.ProductNotFoundException;
import com.balaji.cosmos.kafka.ProductPublisher;
import com.balaji.cosmos.repositories.ProductRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * <h1>Product service - Performs crud operation using repository</h1>
 *
 */
@Service
@AllArgsConstructor
@Slf4j
public class ProductService {

	// ProductRepository - Talks with cosmos database
	private ProductRepository productRepository;
	private ProductPublisher productPublisher;

	/**
	 * retrieves list of all products
	 * 
	 * @return List<Product> returns list of all products.
	 */
	public List<Product> getProducts() {
		List<Product> products = new ArrayList<>();
		productRepository.findAll().forEach(products::add);
		return products;
	}

	/**
	 * retrieves product by id
	 * 
	 * @param productId Id of the product to be retrieved
	 * @throws ProductNotFoundException throws, if no product with given id found
	 * @return Product This returns product that matches the given id
	 */
	public Product getProductById(String id) throws ProductNotFoundException {
		Optional<Product> optionalProduct = productRepository.findById(id);
		if (optionalProduct.isPresent()) {
			log.info("Found product with id: {}", id);
			return optionalProduct.get();
		} else {
			log.error("Product with id: {} not found", id);
			throw new ProductNotFoundException("Product with id: " + id + " not found");
		}
	}

	/**
	 * Saves product to database
	 * 
	 * @param product Product that is passed as request body
	 * @return Product This returns saved product
	 */
	public ApprovalResponse saveProduct(Product product) {
		/*
		 * product.setId("123454124"); CosmosClient client = new CosmosClientBuilder()
		 * .endpoint(AccountSettings.HOST) .key(AccountSettings.MASTER_KEY)
		 * .consistencyLevel(ConsistencyLevel.EVENTUAL) .buildClient(); CosmosContainer
		 * container = client.getDatabase("stores").getContainer("product");
		 * CosmosItemRequestOptions options = new CosmosItemRequestOptions();
		 * options.setPreTriggerInclude( Arrays.asList("Trig1") );
		 * CosmosItemResponse<Product> response = container.createItem(product,
		 * options); System.out.println(response.getItem()); return response.getItem();
		 */
		product.setApprovalStatus("Submitted for approval");
		productPublisher.publishForApproval(product);
		return new ApprovalResponse("Submitted for approval", "Product has been submitted for approval by Admin team");
	}

	public ResponseEntity<ApprovalResponse> saveProductWithRest(Product product) {

		RestTemplate restTemplate = new RestTemplateBuilder()
				.rootUri(new UriTemplate("http://localhost:9000").toString()).basicAuthentication("user", "password")
				.build();
		product.setApprovalStatus("Submitted for approval");
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<Product> request = new HttpEntity<>(product, headers);

		try {
			ResponseEntity<ApprovalResponse> responseEntity = restTemplate.postForEntity("/approvals", request,
					ApprovalResponse.class);
			ApprovalResponse response = responseEntity.getBody();
			return ResponseEntity.status(HttpStatus.CREATED).body(response);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
					.body(new ApprovalResponse(e.getLocalizedMessage(), e.getMessage()));
		}
	}

	/**
	 * deletes product by id
	 * 
	 * @param productId Id of the product to be deleted
	 * @throws ProductNotFoundException throws, if no product with given id found
	 * @return ResponseEntity<String> return successful message if deleted
	 */
	public void deleteProduct(String id) throws ProductNotFoundException {
		Product product = this.getProductById(id);
		log.info("Found product with id: {}", id);
		productRepository.deleteById(id, new PartitionKey(product.getProductId()));
	}

	/**
	 * updates product
	 * 
	 * @param product Product that is passed as request body
	 * @return Product returns the updated product
	 */
	public Product updateProduct(Product product) throws ProductNotFoundException {
		this.getProductById(product.getId());
		return productRepository.save(product);
	}

	public Product saveApprovedProduct(Product product) {
		Product savedProduct = productRepository.save(product);
		return savedProduct;
	}
}
