/**
 * 
 */
package com.poc.shoppingcart.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.poc.shoppingcart.entity.Address;
import com.poc.shoppingcart.repository.AddressRepository;

/**
 * @author Sayanta.Ganguly
 *
 */

@Component
@Service
public class AddressMgmtServiceImpl implements AddressMgmtService {

	@Autowired
	AddressRepository addressRepository;
	
	@Override
	public Address putData(Address address) {
		// TODO Auto-generated method stub
		return addressRepository.save(address);
	}

	@Override
	public Address retrieveCustomerByAddressId(Integer addressId) {
		// TODO Auto-generated method stub
		Address address = addressRepository.getOne(addressId);
		return address;
	}

}
