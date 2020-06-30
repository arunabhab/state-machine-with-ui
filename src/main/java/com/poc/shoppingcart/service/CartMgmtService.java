package com.poc.shoppingcart.service;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.poc.shoppingcart.entity.Cart;


/**
 * @author ArunabhaB
 *
 */

@Component
@Service
public interface CartMgmtService {
	
	public List<Cart> retrieveData();
	
	public int putData(Cart cust);

}
