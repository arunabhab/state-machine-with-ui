package com.poc.shoppingcart.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.poc.shoppingcart.entity.Cart;

/**
 * @author ArunabhaB
 *
 */

public interface CartRepository extends JpaRepository<Cart, Object> {
	
}
