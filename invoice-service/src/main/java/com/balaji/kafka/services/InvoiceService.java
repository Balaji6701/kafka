package com.balaji.kafka.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.balaji.kafka.entity.Invoice;
import com.balaji.kafka.repository.InvoiceRepository;

@Service
public class InvoiceService {
	
	@Autowired
	private InvoiceRepository invoiceRepository;

	public void createInvoice(Invoice invoice) {
		invoiceRepository.save(invoice);
	}
	
	public Invoice getInvoiceById(int id) {
		return invoiceRepository.findById(id).get();
	}
	
	public Invoice getInvoiceByOrderId(int orderId) {
		return invoiceRepository.findByOrderId(orderId).get();
	}
}
