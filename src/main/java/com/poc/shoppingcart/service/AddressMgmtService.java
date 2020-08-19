/**
 * 
 */
package com.poc.shoppingcart.service;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.poc.shoppingcart.entity.Address;


/**
 * @author Sayanta.Ganguly
 *
 */

@Component
@Service
public interface AddressMgmtService {

	public Address putData(Address address);

	Address retrieveCustomerByAddressId(Integer addressId);
	
}
