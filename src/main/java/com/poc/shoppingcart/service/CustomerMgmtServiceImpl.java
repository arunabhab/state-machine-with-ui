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
public class CustomerMgmtServiceImpl implements CustomerMgmtService{

	@Override
	public List<Customer> retrieveData() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int putData(Customer cust) {
		// TODO Auto-generated method stub
		return 0;
	}

	

}
