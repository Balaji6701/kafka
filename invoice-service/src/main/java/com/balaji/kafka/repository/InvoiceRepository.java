package com.balaji.kafka.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.balaji.kafka.entity.Invoice;

@Repository
public interface InvoiceRepository extends CrudRepository<Invoice, Integer>{
	
	Optional<Invoice> findByOrderId(int orderId);
}
