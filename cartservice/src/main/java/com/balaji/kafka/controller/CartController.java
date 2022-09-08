package com.balaji.kafka.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.balaji.kafka.entity.Cart;
import com.balaji.kafka.entity.Product;
import com.balaji.kafka.services.CartService;

@RestController
@RequestMapping("/carts")
public class CartController {
	
	@Autowired
	private CartService cartService;
	
	@GetMapping("/{id}")
	public Cart getCart(@PathVariable("id")Integer cartId) {
		return cartService.getCartById(cartId);
	}
	
	@PostMapping("/{id}")
	public ResponseEntity<Cart> addProduct(@PathVariable("id")Integer cartId ,@RequestBody Product product){
		Cart cart = cartService.addProductToCart(cartId, product);
		return ResponseEntity.ok(cart);
	}
	
	@GetMapping("/placeOrder/{id}")
	public ResponseEntity<String> placeOrder(@PathVariable("id") int cartId){
		cartService.placeOrder(cartId);
		return new ResponseEntity<String>(HttpStatus.CREATED);
	}
	
}
