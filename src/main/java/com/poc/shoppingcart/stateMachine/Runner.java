package com.poc.shoppingcart.stateMachine;

import java.util.Date;
import java.util.Scanner;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.statemachine.StateMachine;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import com.poc.shoppingcart.entity.CartTemp;
import com.poc.shoppingcart.entity.Customer;
import com.poc.shoppingcart.entity.Order;
import com.poc.shoppingcart.enums.OrderEvents;
import com.poc.shoppingcart.enums.OrderStates;
import com.poc.shoppingcart.service.CartMgmtService;
import com.poc.shoppingcart.service.CustomerMgmtService;
import com.poc.shoppingcart.service.OrderService;

import lombok.extern.java.Log;

/**
 * @author Sayanta.Ganguly
 *
 */

@Log
@Component
public class Runner implements ApplicationRunner {

	/*
	 * public Runner(StateMachineFactory<OrderStates, OrderEvents> factory) {
	 * this.factory = factory; }
	 */

	/*
	 * @Autowired CustomerMgmtService customerMgmtService;
	 * 
	 * @Autowired CartMgmtService cartMgmtService;
	 * 
	 * private final OrderService orderService;
	 * 
	 * Runner(OrderService orderService) { this.orderService = orderService; }
	 * 
	 * @Override public void run(ApplicationArguments args) throws Exception {
	 * 
	 * 
	 * Long orderId = 13232L; StateMachine<OrderStates, OrderEvents> machine =
	 * this.factory.getStateMachine(Long.toString(orderId));
	 * machine.getExtendedState().getVariables().putIfAbsent("orderId", orderId);
	 * machine.start(); //log.info("current state: " +
	 * machine.getState().getId().name());
	 * System.out.println(machine.getState().getId().name());
	 * machine.sendEvent(OrderEvents.PAY);
	 * System.out.println(machine.getState().getId().name()); Message<OrderEvents>
	 * eventsMessage = MessageBuilder .withPayload(OrderEvents.FULFILL)
	 * .setHeaderIfAbsent("a", "b") .build(); machine.sendEvent(eventsMessage);
	 * System.out.println(machine.getState().getId().name());
	 * 
	 * //OrderService orderervice = new OrderService(); Order order =
	 * this.orderService.create(new Date());
	 * 
	 * System.out.println("after calling create() ");
	 * 
	 * Scanner myObj = new Scanner(System.in); // Create a Scanner object
	 * System.out.println("Enter username");
	 * 
	 * String userName = myObj.nextLine();
	 * 
	 * System.out.println(userName);
	 * 
	 * Customer customer = customerMgmtService.retrieveCustomer(userName);
	 * 
	 * System.out.println("Products available : ");
	 * 
	 * String inp = new String("No"); String prodList=null; while (inp
	 * .equalsIgnoreCase("no")) {
	 * 
	 * System.out.println("1.A\n 2.B\n 3.C\n 4.D ");
	 * 
	 * System.out.println("Enter product names seperated by ','");
	 * 
	 * prodList = myObj.nextLine();
	 * 
	 * 
	 * System.out.println("Do you want to checkout? (Yes/No)");
	 * 
	 * inp= myObj.nextLine(); }
	 * 
	 * if(ObjectUtils.isEmpty(customer)) { System.out.println("Customer not found");
	 * System.out.println("Do you wish to register? (Yes/No)");
	 * inp=myObj.nextLine(); if(inp.equalsIgnoreCase("yes")) { //registration code
	 * customer=new Customer(); customer.setCustName(userName);
	 * customer=customerMgmtService.putData(customer);
	 * System.out.println("Cust saved with id = "+customer.getCustId()); CartTemp
	 * cartTemp=cartMgmtService.putDataTemp(customer.getCustId(),prodList);
	 * System.out.println("Items saved with cart id "+cartTemp.getCartId()); }else {
	 * System.out.println("Registration not opted. Discarding cart and quitting"); }
	 * }else { System.out.println("Customer found with id = "+customer.getCustId());
	 * System.out.println(customer.toString()); CartTemp
	 * cartTemp=cartMgmtService.putDataTemp(customer.getCustId(),prodList);
	 * System.out.println("Items saved with cart id "+cartTemp.getCartId()); }
	 * 
	 * 
	 * 
	 * 
	 * StateMachine<OrderStates, OrderEvents> paymentStateMachine =
	 * orderService.pay(order.getId(), UUID.randomUUID().toString());
	 * System.out.println("after calling pay() : " +
	 * paymentStateMachine.getState().getId().name());
	 * System.out.println("Order : "+ orderService.byId(order.getId()));
	 * 
	 * StateMachine<OrderStates, OrderEvents> fulfilledStateMachine =
	 * orderService.fulfill(order.getId());
	 * System.out.println("after calling fulfill() : "+
	 * fulfilledStateMachine.getState().getId().name());
	 * System.out.println("Order : " + orderService.byId(order.getId()));
	 * 
	 * StateMachine<OrderStates, OrderEvents> checkoutStateMachine =
	 * orderService.checkOut(order.getId());
	 * System.out.println("after calling checkOut() : " +
	 * checkoutStateMachine.getState().getId().name());
	 * System.out.println("Order : "+ orderService.byId(order.getId()));
	 * 
	 * StateMachine<OrderStates, OrderEvents> saveInCartTempStateMachine =
	 * orderService.saveInCartTemp(order.getId());
	 * System.out.println("after calling checkOut() : " +
	 * saveInCartTempStateMachine.getState().getId().name());
	 * System.out.println("Order : "+ orderService.byId(order.getId()));
	 * 
	 * }
	 * 
	 * 
	 * long testOrderId = 7767045721079294177L; Order order =
	 * this.orderService.create(new Date()); //TODO StateMachine<OrderStates,
	 * OrderEvents> paymentStateMachine = orderService.pay(order.getId(),
	 * UUID.randomUUID().toString()); StateMachine<OrderStates, OrderEvents>
	 * paymentStateMachine = orderService.pay(testOrderId,
	 * UUID.randomUUID().toString()); System.out.println("after calling pay() : " +
	 * paymentStateMachine.getState().getId().name());
	 * System.out.println("Order : "+ orderService.byId(testOrderId));
	 */
	
	private final OrderService orderService;
	
	Runner(OrderService orderService) { this.orderService = orderService; }
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		
		/*
		 * Long orderId = 13232L; StateMachine<OrderStates, OrderEvents> machine =
		 * this.factory.getStateMachine(Long.toString(orderId));
		 * machine.getExtendedState().getVariables().putIfAbsent("orderId", orderId);
		 * machine.start(); //log.info("current state: " +
		 * machine.getState().getId().name());
		 * System.out.println(machine.getState().getId().name());
		 * machine.sendEvent(OrderEvents.PAY);
		 * System.out.println(machine.getState().getId().name()); Message<OrderEvents>
		 * eventsMessage = MessageBuilder .withPayload(OrderEvents.FULFILL)
		 * .setHeaderIfAbsent("a", "b") .build(); machine.sendEvent(eventsMessage);
		 * System.out.println(machine.getState().getId().name());
		 */
		//OrderService orderervice = new OrderService();
		//TODO Order order = this.orderService.create(new Date());
		
		System.out.println("after calling create() ");
		
		/*
		 * 
		 * TODO
		 * 
		 * 
		 * 
		 * long testOrderId = 7767045721079294177L; //TODO StateMachine<OrderStates,
		 * OrderEvents> paymentStateMachine = orderService.pay(order.getId(),
		 * UUID.randomUUID().toString()); StateMachine<OrderStates, OrderEvents>
		 * paymentStateMachine = orderService.pay(testOrderId,
		 * UUID.randomUUID().toString()); System.out.println("after calling pay() : " +
		 * paymentStateMachine.getState().getId().name());
		 * System.out.println("Order : "+ orderService.byId(testOrderId));
		 */
		
		/*
		 * StateMachine<OrderStates, OrderEvents> fulfilledStateMachine =
		 * orderService.fulfill(order.getId());
		 * System.out.println("after calling fulfill() : "+
		 * fulfilledStateMachine.getState().getId().name());
		 * System.out.println("Order : " + orderService.byId(order.getId()));
		 * 
		 * StateMachine<OrderStates, OrderEvents> checkoutStateMachine =
		 * orderService.checkOut(order.getId());
		 * System.out.println("after calling checkOut() : " +
		 * checkoutStateMachine.getState().getId().name());
		 * System.out.println("Order : "+ orderService.byId(order.getId()));
		 * 
		 * StateMachine<OrderStates, OrderEvents> saveInCartTempStateMachine =
		 * orderService.saveInCartTemp(order.getId());
		 * System.out.println("after calling checkOut() : " +
		 * saveInCartTempStateMachine.getState().getId().name());
		 * System.out.println("Order : "+ orderService.byId(order.getId()));
		 */
	}

}
