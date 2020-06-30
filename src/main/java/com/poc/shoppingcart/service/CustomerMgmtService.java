package com.poc.shoppingcart.service;

import java.util.List;

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
	
	public List<Customer> retrieveData();
	
	public int putData(Customer cust);

}
