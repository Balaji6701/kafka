package com.balaji.kafka.repository;

import org.springframework.data.repository.CrudRepository;

import com.balaji.kafka.entity.Cart;

public interface CartRepository extends CrudRepository<Cart, Integer>{

}
