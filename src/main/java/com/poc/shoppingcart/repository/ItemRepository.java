package com.poc.shoppingcart.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.poc.shoppingcart.entity.Item;

/**
 * @author ArunabhaB
 *
 */

public interface ItemRepository extends JpaRepository<Item, Object> {
	
}
