package com.balaji.kafka.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.balaji.kafka.entity.Invoice;
import com.balaji.kafka.entity.Order;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class OrderConsumer {

	@Autowired
	private InvoiceService invoiceService;

	@KafkaListener(topics = "t-processed-order", concurrency = "3", groupId = "cg-invoice")
	public void readOrder(Order order) {
		log.info("order received: {}", order);
		createInvoice(order);
	}

	private void createInvoice(Order order) {
		Invoice invoice = new Invoice();
		invoice.setDeliveryAddress(order.getDeliveryAddress());
		invoice.setOrderDate(order.getOrderDate());
		invoice.setTotalPrice(order.getTotalPrice());
		invoice.setOrderId(order.getOrderId());
		invoiceService.createInvoice(invoice);
	}
}
