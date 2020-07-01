package com.poc.shoppingcart.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.poc.shoppingcart.entity.Customer;
import com.poc.shoppingcart.repository.CustomerRepository;


/**
 * @author ArunabhaB
 *
 */
@Component
@Service
public class CustomerMgmtServiceImpl implements CustomerMgmtService{

	@Autowired
	CustomerRepository customerRepository;
	

	@Override
	public Customer putData(Customer cust) {
		return customerRepository.save(cust);
	}

	@Override
	public Customer retrieveCustomer(String name) {
		Customer customer=customerRepository.findByCustName(name);
		return customer;
	}

	

}
