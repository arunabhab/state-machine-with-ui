package com.poc.shoppingcart.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.poc.shoppingcart.entity.CartTemp;

/**
 * @author ArunabhaB
 *
 */

public interface CartTempRepository extends JpaRepository<CartTemp, Object> {

	Optional<CartTemp> findByCustId(Integer custId);

}
