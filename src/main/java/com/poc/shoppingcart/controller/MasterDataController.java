package com.poc.shoppingcart.controller;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.statemachine.StateMachine;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.poc.shoppingcart.entity.Item;
import com.poc.shoppingcart.entity.Order;
import com.poc.shoppingcart.enums.OrderEvents;
import com.poc.shoppingcart.enums.OrderStates;
import com.poc.shoppingcart.service.ItemService;
import com.poc.shoppingcart.service.OrderService;

/**
 * @author ArunabhaB
 *
 */

@RestController
@RequestMapping("/masterdata")
public class MasterDataController {


	
	@Autowired
	OrderService orderService;

	
	
	
	@RequestMapping(value = "/pay", method = RequestMethod.GET,produces={"application/json","application/xml"})
	public String pay() throws Exception {
		Order order = this.orderService.create(new Date());
		StateMachine<OrderStates, OrderEvents> paymentStateMachine = orderService.pay(order.getId(), UUID.randomUUID().toString());
		System.out.println("after calling pay() : " + paymentStateMachine.getState().getId().name());
		String s= "Order : "+ orderService.byId(order.getId());
		
		return s;
	}



}
