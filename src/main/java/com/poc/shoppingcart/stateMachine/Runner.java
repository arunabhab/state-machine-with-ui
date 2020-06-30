package com.poc.shoppingcart.stateMachine;

import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.stereotype.Component;

import com.poc.shoppingcart.entity.Order;
import com.poc.shoppingcart.enums.OrderEvents;
import com.poc.shoppingcart.enums.OrderStates;
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
		Order order = this.orderService.create(new Date());
		
		System.out.println("after calling create() ");
		
		StateMachine<OrderStates, OrderEvents> paymentStateMachine = orderService.pay(order.getId(), UUID.randomUUID().toString());
		System.out.println("after calling pay() : " + paymentStateMachine.getState().getId().name());
		System.out.println("Order : "+ orderService.byId(order.getId()));
		
		StateMachine<OrderStates, OrderEvents> fulfilledStateMachine = orderService.fulfill(order.getId());
		System.out.println("after calling fulfill() : "+ fulfilledStateMachine.getState().getId().name());
		System.out.println("Order : " + orderService.byId(order.getId()));
		
		StateMachine<OrderStates, OrderEvents> checkoutStateMachine = orderService.checkOut(order.getId());
		System.out.println("after calling checkOut() : " + checkoutStateMachine.getState().getId().name());
		System.out.println("Order : "+ orderService.byId(order.getId()));
		
		StateMachine<OrderStates, OrderEvents> saveInCartTempStateMachine = orderService.saveInCartTemp(order.getId());
		System.out.println("after calling checkOut() : " + saveInCartTempStateMachine.getState().getId().name());
		System.out.println("Order : "+ orderService.byId(order.getId()));
	}

}
