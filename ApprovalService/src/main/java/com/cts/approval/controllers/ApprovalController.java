package com.cts.approval.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.balaji.cosmos.entities.ApprovalResponse;
import com.balaji.cosmos.entities.Product;
import com.cts.approval.services.ApprovalService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/approvals")
@AllArgsConstructor
public class ApprovalController {
	
	private ApprovalService approvalService;
	
	@GetMapping("/approve/{id}")
	public ResponseEntity<ApprovalResponse> approveProduct(@PathVariable("id") String id) {
		approvalService.approveProduct(id);
		return ResponseEntity.ok(new ApprovalResponse("Approved","Product with id:" + id + "  approved"));
	}
	
	@GetMapping("/rest/approve/{id}")
	public ResponseEntity<ApprovalResponse> approveProductRest(@PathVariable("id") String id) {
		approvalService.approveProductRest(id);
		return ResponseEntity.ok(new ApprovalResponse("Approved","Product with id:" + id + "  approved"));
	}
	
	@GetMapping("/rest/reject/{id}")
	public ResponseEntity<ApprovalResponse> rejectProductRest(@PathVariable("id") String id) {
		approvalService.rejectProductRest(id);
		return ResponseEntity.ok(new ApprovalResponse("Rejected","Product with id:" + id + "  rejected"));
	}
	
	@PostMapping
	public ApprovalResponse submitForApproval(@RequestBody Product product) {
		approvalService.saveProduct(product);
		return new ApprovalResponse("Submitted for approval", "Product has been received for approval by Admin team");
	}
	
	@GetMapping
	public ResponseEntity<List<Product>> getAllApprovals(){
		return ResponseEntity.ok(approvalService.getAllProductsForApproval());
	}
	
	@GetMapping("/reject/{id}")
	public ResponseEntity<ApprovalResponse> rejectProduct(@PathVariable("id") String id) {
		approvalService.rejectProduct(id);
		return ResponseEntity.ok(new ApprovalResponse("Rejected","Product with id:" + id + "  rejected"));
	}
		
}
