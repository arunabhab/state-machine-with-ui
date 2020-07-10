package com.poc.shoppingcart.service;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.poc.shoppingcart.entity.Customer;


/**
 * @author ArunabhaB
 *
 */

@Component
@Service
public interface CustomerMgmtService {
		
	public Customer putData(Customer cust);

	Customer retrieveCustomer(String name);

}
