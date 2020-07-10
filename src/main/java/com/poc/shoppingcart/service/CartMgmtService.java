package com.poc.shoppingcart.service;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.poc.shoppingcart.entity.Cart;
import com.poc.shoppingcart.entity.CartTemp;


/**
 * @author ArunabhaB
 *
 */

@Component
@Service
public interface CartMgmtService {
	
	public List<Cart> retrieveData();
	
	public int putData(Cart cust);
	
//	public CartTemp putDataTemp(Integer custId,String list);
	
	
	public CartTemp updateItems(Integer cartId, String itemList);
	
	public CartTemp getCart(Integer cartId);
	
	public CartTemp getCartByCustId(Integer custId);

	public CartTemp deleteCart(Integer cartId);

	CartTemp createNewCart(Integer custId, Long orderId);

}
