package com.balaji.kafka.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.balaji.kafka.entity.Cart;
import com.balaji.kafka.entity.Order;
import com.balaji.kafka.entity.Product;
import com.balaji.kafka.repository.CartRepository;

@Service
public class CartService {

	@Autowired
	private CartRepository cartRepository;
	@Autowired
	private OrderPublisher orderPublisher;
	
	public Cart getCartById(int cartId) {
		return cartRepository.findById(cartId).get();
	}

	public Cart addProductToCart(int cartId, Product product) {
		Optional<Cart> opCart = cartRepository.findById(cartId);
		Cart cart;
		if (opCart.isPresent()) {
			cart = opCart.get();
		} else {
			cart = new Cart();
			cart.setProducts(new ArrayList<>());
		}
		cart.getProducts().add(product);
		return saveCart(cart);
	}

	public Cart saveCart(Cart cart) {
		return cartRepository.save(cart);
	}
	
	public void placeOrder(int cartId) {
		Order order = new Order();
		Cart cart = getCartById(cartId);
		order.setProducts(cart.getProducts());
		order.setTotalPrice(calculateTotalPrice(cart.getProducts()));
		order.setDeliveryAddress("address");
		order.setOrderDate(LocalDate.now());
		orderPublisher.publishOrder(order);
	}
	
	private double calculateTotalPrice(List<Product> products) {
		return products.stream().map(product -> product.getPrice() * product.getQuantity()).reduce(0.0, (a,b) -> a+b);
	}
}
