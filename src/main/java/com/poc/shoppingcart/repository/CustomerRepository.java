package com.poc.shoppingcart.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.poc.shoppingcart.entity.Customer;

/**
 * @author ArunabhaB
 *
 */

public interface CustomerRepository extends JpaRepository<Customer, Object> {
	
}
