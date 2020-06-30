package com.poc.shoppingcart.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.poc.shoppingcart.entity.Order;

/**
 * @author Sayanta.Ganguly
 *
 */

public interface OrderRepository extends JpaRepository<Order, Long>{


}
