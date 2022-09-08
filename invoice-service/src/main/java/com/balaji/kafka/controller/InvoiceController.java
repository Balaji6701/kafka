package com.balaji.kafka.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.balaji.kafka.entity.Invoice;
import com.balaji.kafka.services.InvoiceService;

@RestController
@RequestMapping("/invoices")
public class InvoiceController {
	
	@Autowired
	private InvoiceService invoiceService;
	
	@GetMapping("/invoice/{id}")
	public Invoice getInvoice(@PathVariable("id") int id) {
		return invoiceService.getInvoiceById(id);
	}
	
	@GetMapping("/order/{id}")
	public Invoice getInvoiceByOrderId(@PathVariable("id") int orderId) {
		return invoiceService.getInvoiceByOrderId(orderId);
	}
}
