package com.poc.shoppingcart.service;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.poc.shoppingcart.entity.Cart;
import com.poc.shoppingcart.entity.CartTemp;
import com.poc.shoppingcart.repository.CartTempRepository;


/**
 * @author ArunabhaB
 *
 */
@Component
@Service
public class CartMgmtServiceImpl implements CartMgmtService{

	@Autowired
	CartTempRepository cartTempRepo;
	
	@Override
	public List<Cart> retrieveData() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int putData(Cart cust) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public CartTemp putDataTemp(Integer custId,String list) {
		
		
		CartTemp cartTemp=new CartTemp(custId,list,new Timestamp(System.currentTimeMillis()));
		return cartTempRepo.save(cartTemp);
		
	}

		

}
