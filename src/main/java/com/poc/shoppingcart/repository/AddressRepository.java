/**
 * 
 */
package com.poc.shoppingcart.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.poc.shoppingcart.entity.Address;


/**
 * @author Sayanta.Ganguly
 *
 */
public interface AddressRepository extends JpaRepository<Address, Object> {

}
