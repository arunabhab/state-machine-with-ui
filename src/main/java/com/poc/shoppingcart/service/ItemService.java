package com.poc.shoppingcart.service;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.poc.shoppingcart.entity.Item;


/**
 * @author ArunabhaB
 *
 */

@Component
@Service
public interface ItemService {
	
	public List<Item> getItemList();


}
