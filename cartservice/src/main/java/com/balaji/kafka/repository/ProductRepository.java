package com.balaji.kafka.repository;

import org.springframework.data.repository.CrudRepository;

import com.balaji.kafka.entity.Product;

public interface ProductRepository extends CrudRepository<Product, Integer>{

}
