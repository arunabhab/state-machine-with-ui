package com.poc.shoppingcart.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.statemachine.StateMachine;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.poc.shoppingcart.entity.CartDAO;
import com.poc.shoppingcart.entity.CartTemp;
import com.poc.shoppingcart.entity.Order;
import com.poc.shoppingcart.enums.OrderEvents;
import com.poc.shoppingcart.enums.OrderStates;
import com.poc.shoppingcart.service.CartMgmtService;
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

	@Autowired
	CartMgmtService cartMgmtService;

	@RequestMapping(value = "/createCart", method = RequestMethod.POST, produces = { "application/json",
			"application/xml" })
	@ResponseBody
	public CartDAO createCart(@RequestParam Integer custId) throws Exception {

		Order order = this.orderService.create(new Date());

		CartTemp cartTemp = cartMgmtService.createNewCart(custId, order.getId());

		CartDAO cartDAO = new CartDAO();
		cartDAO.setCartId(cartTemp.getCartId().toString());
		cartDAO.setOrderId(order.getId());

		return cartDAO;

	}

	@RequestMapping(value = "/addItems", method = RequestMethod.POST, produces = { "application/json",
			"application/xml" })
	public String addItems(@RequestParam("cartId") Integer cartId, @RequestParam("items") String itemList,
			@RequestParam("orderId") Long orderId) throws Exception {

		CartTemp cartTemp = cartMgmtService.updateItems(cartId, itemList);
		StateMachine<OrderStates, OrderEvents> paymentStateMachine = orderService.saveCart(orderId);

		return cartTemp.getCartId().toString();

	}

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String home() {
		return "upload";
	}

	@RequestMapping(value = "/getCart", method = RequestMethod.GET, produces = { "application/json",
			"application/xml" })
	public CartDAO getCart(@RequestParam("custId") Integer custId) throws Exception {
		/*
		 * Order order = this.orderService.create(new Date()); StateMachine<OrderStates,
		 * OrderEvents> paymentStateMachine = orderService.pay(order.getId(),
		 * UUID.randomUUID().toString()); System.out.println("after calling pay() : " +
		 * paymentStateMachine.getState().getId().name()); String s = "Order : " +
		 * orderService.byId(order.getId());
		 * 
		 * return s;
		 */

		CartTemp cartTemp = cartMgmtService.getCartByCustId(custId);
//		StateMachine<OrderStates, OrderEvents> paymentStateMachine = orderService.saveCart(orderId);
		Order order = orderService.getOrderDtls(cartTemp.getOrderId());
		CartDAO cartDAO = new CartDAO(cartTemp.getCartId().toString(), cartTemp.getOrderId(), order.getState());
		return cartDAO;
	}

	@RequestMapping(value = "/getCartItems", method = RequestMethod.GET, produces = { "application/json",
			"application/xml" })
	public String getCartItems(@RequestParam("cartId") Integer cartId, @RequestParam("orderId") Long orderId)
			throws Exception {
		/*
		 * Order order = this.orderService.create(new Date()); StateMachine<OrderStates,
		 * OrderEvents> paymentStateMachine = orderService.pay(order.getId(),
		 * UUID.randomUUID().toString()); System.out.println("after calling pay() : " +
		 * paymentStateMachine.getState().getId().name()); String s = "Order : " +
		 * orderService.byId(order.getId());
		 * 
		 * return s;
		 */

		CartTemp cartTemp = cartMgmtService.getCart(cartId);
//StateMachine<OrderStates, OrderEvents> paymentStateMachine = orderService.saveCart(orderId);
		return cartTemp.getItemList().toString();
	}

	@RequestMapping(value = "/updateItems", method = RequestMethod.POST, produces = { "application/json",
			"application/xml" })
	public String updateItems(@RequestParam("cartId") Integer cartId, @RequestParam("items") String itemList,
			@RequestParam("orderId") Long orderId) throws Exception {
		// Order order = this.orderService.create(new Date());
		// StateMachine<OrderStates, OrderEvents> paymentStateMachine =
		// orderService.pay(order.getId(),
		// UUID.randomUUID().toString());
		// System.out.println("after calling pay() : " +
		// paymentStateMachine.getState().getId().name());
		// String s = "Order : " + orderService.byId(order.getId());
		//
		// return s;

		CartTemp cartTemp = cartMgmtService.updateItems(cartId, itemList);
		StateMachine<OrderStates, OrderEvents> paymentStateMachine = orderService.retrieveCart(orderId);
		StateMachine<OrderStates, OrderEvents> paymentStateMachine1 = orderService.updateCart(orderId);

		return cartTemp.getCartId().toString();

	}

	@RequestMapping(value = "/deleteCart", method = RequestMethod.GET, produces = { "application/json",
			"application/xml" })
	public void deleteCart(@RequestParam("cartId") Integer cartId, @RequestParam("orderId") Long orderId)
			throws Exception {
		/*
		 * Order order = this.orderService.create(new Date()); StateMachine<OrderStates,
		 * OrderEvents> paymentStateMachine = orderService.pay(order.getId(),
		 * UUID.randomUUID().toString()); System.out.println("after calling pay() : " +
		 * paymentStateMachine.getState().getId().name()); String s = "Order : " +
		 * orderService.byId(order.getId());
		 * 
		 * return s;
		 */

		cartMgmtService.deleteCart(cartId);
		StateMachine<OrderStates, OrderEvents> paymentStateMachine = orderService.retrieveCart(orderId);
		StateMachine<OrderStates, OrderEvents> paymentStateMachine1 = orderService.cancelCart(orderId);

	}

	@RequestMapping(value = "/deleteCartByCustId", method = RequestMethod.GET, produces = { "application/json",
			"application/xml" })
	public void getCartByCustId(@RequestParam("custId") Integer custId) throws Exception {
		/*
		 * Order order = this.orderService.create(new Date()); StateMachine<OrderStates,
		 * OrderEvents> paymentStateMachine = orderService.pay(order.getId(),
		 * UUID.randomUUID().toString()); System.out.println("after calling pay() : " +
		 * paymentStateMachine.getState().getId().name()); String s = "Order : " +
		 * orderService.byId(order.getId());
		 * 
		 * return s;
		 */
		CartTemp cartTemp = cartMgmtService.getCartByCustId(custId);
		cartMgmtService.deleteCart(cartTemp.getCartId());
		StateMachine<OrderStates, OrderEvents> paymentStateMachine = orderService.retrieveCart(cartTemp.getOrderId());
		StateMachine<OrderStates, OrderEvents> paymentStateMachine1 = orderService.cancelCart(cartTemp.getOrderId());

	}

	@RequestMapping(value = "/pay", method = RequestMethod.POST, produces = {
	  "application/json", "application/xml" }) public String pay(@RequestParam("custId") Integer custId, 
			  @RequestParam("orderId") Long orderId) throws Exception
	  {
		Order order = orderService.getOrderDtls(orderId);
	  StateMachine<OrderStates, OrderEvents> paymentStateMachine =
	  orderService.pay(order.getId()); System.out.println("after calling pay() : "
	  + paymentStateMachine.getState().getId().name()); String s = "Order : " +
	  orderService.byId(order.getId());
	  
	  return s;
	  }
	 

}
