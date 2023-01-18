package com.cts.approval.repositories;

import org.springframework.data.repository.CrudRepository;

import com.balaji.cosmos.entities.Product;

public interface ProductRepository extends CrudRepository<Product, String>{

}
