package com.poc.shoppingcart.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.poc.shoppingcart.entity.Item;
import com.poc.shoppingcart.service.ItemService;

/**
 * @author ArunabhaB
 *
 */

@RestController
@RequestMapping("/masterdata")
public class MasterDataController {


	
	@Autowired
	ItemService itemService;
	

	@ResponseBody
	@RequestMapping(value = "/getItems", method = RequestMethod.GET,produces={"application/json","application/xml"})
	public List<Item> getString() throws Exception {
		List<Item> items=itemService.getItemList();
		return items;

	}
	



}
