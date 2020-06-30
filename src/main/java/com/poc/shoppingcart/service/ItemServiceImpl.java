package com.poc.shoppingcart.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.poc.shoppingcart.entity.Customer;
import com.poc.shoppingcart.entity.Item;
import com.poc.shoppingcart.repository.ItemRepository;


/**
 * @author ArunabhaB
 *
 */
@Component
@Service
public class ItemServiceImpl implements ItemService{

	@Autowired
	ItemRepository itemRepository;
	
	@Override
	public List<Item> getItemList() {
		return itemRepository.findAll();
	}


}
