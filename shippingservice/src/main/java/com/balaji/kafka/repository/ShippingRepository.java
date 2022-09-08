package com.balaji.kafka.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.balaji.kafka.entity.ShippingDetails;

@Repository
public interface ShippingRepository extends CrudRepository<ShippingDetails, Integer>{

}
