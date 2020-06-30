package com.poc.shoppingcart.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.poc.shoppingcart.entity.Cart;
import com.poc.shoppingcart.entity.CartList;

/**
 * @author ArunabhaB
 *
 */

public interface CartListRepository extends JpaRepository<CartList, Object> {
	
}
