package com.poc.shoppingcart.service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

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
public class CartMgmtServiceImpl implements CartMgmtService {

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

//	@Override
//	public CartTemp putDataTemp(Integer custId, String list) {
//		CartTemp cartTemp = new CartTemp(custId, list, new Timestamp(System.currentTimeMillis()));
//		return cartTempRepo.save(cartTemp);
//
//	}

	@Override
	public CartTemp createNewCart(Integer custId, Long orderId) {
		CartTemp cartTemp = new CartTemp(custId, new Timestamp(System.currentTimeMillis()), orderId);
		return cartTempRepo.save(cartTemp);
	}

	@Override
	public CartTemp updateItems(Integer cartId, String itemList) {
		CartTemp cartTemp = cartTempRepo.getOne(cartId);
		cartTemp.setItemList(itemList);
		return cartTempRepo.save(cartTemp);
	}

	@Override
	public CartTemp getCart(Integer cartId) {
		CartTemp cartTemp = cartTempRepo.getOne(cartId);
		return cartTemp;
	}

	@Override
	public CartTemp deleteCart(Integer cartId) {
		cartTempRepo.deleteById(cartId);
		return null;
	}

	@Override
	public CartTemp getCartByCustId(Integer custId) {
		Optional<CartTemp> cartTemp = cartTempRepo.findByCustId(custId);
		if(cartTemp.isPresent()) {
			return cartTemp.get();
		}else {
			return null;
		}
	}

}
