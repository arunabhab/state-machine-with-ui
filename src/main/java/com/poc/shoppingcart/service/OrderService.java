package com.poc.shoppingcart.service;

import java.util.Date;
import java.util.Optional;
import java.util.Random;

import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.access.StateMachineAccess;
import org.springframework.statemachine.access.StateMachineFunction;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.statemachine.state.State;
import org.springframework.statemachine.support.DefaultStateMachineContext;
import org.springframework.statemachine.support.StateMachineInterceptorAdapter;
import org.springframework.statemachine.transition.Transition;
import org.springframework.stereotype.Component;

import com.poc.shoppingcart.entity.Order;
import com.poc.shoppingcart.enums.OrderEvents;
import com.poc.shoppingcart.enums.OrderStates;
import com.poc.shoppingcart.repository.OrderRepository;

/**
 * @author Sayanta.Ganguly
 *
 */
@Component
public class OrderService {

	private final OrderRepository orderRepository;
	private final StateMachineFactory<OrderStates, OrderEvents> factory;

	private static final String ORDER_ID_HEADER = "orderId";

	OrderService(OrderRepository orderRepository, StateMachineFactory<OrderStates, OrderEvents> factory) {
		this.orderRepository = orderRepository;
		this.factory = factory;
	}


	public Order byId(Long id) { return this.orderRepository.getOne(id); }
	
	public Order create(Date when) {
		long generatedLong = new Random().nextLong();


		return this.orderRepository.save(new Order(generatedLong,when, OrderStates.SUBMITTED));
	}
	
	public StateMachine<OrderStates, OrderEvents> saveCart(Long orderId){
		StateMachine<OrderStates, OrderEvents> sm = this.build(orderId);
		
		Message<OrderEvents> paymentMessage = MessageBuilder.withPayload(OrderEvents.SAVE_CART)
											.setHeader(ORDER_ID_HEADER , orderId)
											.build();
		System.out.println("sm=====");
		System.out.println(sm);

		sm.sendEvent(paymentMessage);
		return sm;
	}
	
	public StateMachine<OrderStates, OrderEvents> retrieveCart(Long orderId){
		StateMachine<OrderStates, OrderEvents> sm = this.build(orderId);
		
		Message<OrderEvents> paymentMessage = MessageBuilder.withPayload(OrderEvents.RETRIEVE)
											.setHeader(ORDER_ID_HEADER , orderId)
											.build();
		
		sm.sendEvent(paymentMessage);
		return sm;
	}
	
	public StateMachine<OrderStates, OrderEvents> updateCart(Long orderId){
		StateMachine<OrderStates, OrderEvents> sm = this.build(orderId);
		
		Message<OrderEvents> paymentMessage = MessageBuilder.withPayload(OrderEvents.UPDATE_CART)
											.setHeader(ORDER_ID_HEADER , orderId)
											.build();

		System.out.println("sm=====");
		System.out.println(sm);

		
		sm.sendEvent(paymentMessage);
			return sm;
	}
	
	public StateMachine<OrderStates, OrderEvents> pay(Long orderId){
		StateMachine<OrderStates, OrderEvents> sm = this.build(orderId);
		
		Message<OrderEvents> paymentMessage = MessageBuilder.withPayload(OrderEvents.PAY)
											.setHeader(ORDER_ID_HEADER , orderId)
											.build();
		
		sm.sendEvent(paymentMessage);
		return sm;
	}
	
	public StateMachine<OrderStates, OrderEvents> cancelCart(Long orderId){
		StateMachine<OrderStates, OrderEvents> sm = this.build(orderId);
		
		Message<OrderEvents> paymentMessage = MessageBuilder.withPayload(OrderEvents.CANCEL)
											.setHeader(ORDER_ID_HEADER , orderId)
											.build();

		sm.sendEvent(paymentMessage);
		return sm;
	}

	//same as above, modify accordingly   --->  /*  String paymentConfirmationNumber */  /* .setHeader("paymentConfirmationNumber",paymentConfirmationNumber) */
	public StateMachine<OrderStates, OrderEvents> fulfill(Long orderId){
		StateMachine<OrderStates, OrderEvents> sm = this.build(orderId);
		//make sure that state is updated before this line came
		Message<OrderEvents> fulfillmentMessage = MessageBuilder.withPayload(OrderEvents.PAY)
											.setHeader(ORDER_ID_HEADER , orderId)
											.build();
		
		sm.sendEvent(fulfillmentMessage);
		return sm;
	}
	
	public StateMachine<OrderStates, OrderEvents> saveCust(Long orderId){
		StateMachine<OrderStates, OrderEvents> sm = this.build(orderId);
		
		Message<OrderEvents> registrationMessage = MessageBuilder.withPayload(OrderEvents.REGISTER)
											.setHeader(ORDER_ID_HEADER , orderId)
											.build();
		System.out.println("sm=====");
		System.out.println(sm);

		sm.sendEvent(registrationMessage);
		return sm;
	}
	
	public StateMachine<OrderStates, OrderEvents> saveAddress(Long orderId){
		StateMachine<OrderStates, OrderEvents> sm = this.build(orderId);
		
		Message<OrderEvents> saveAddressMessage = MessageBuilder.withPayload(OrderEvents.SAVE_ADDRESS)
											.setHeader(ORDER_ID_HEADER , orderId)
											.build();
		System.out.println("sm=====");
		System.out.println(sm);

		sm.sendEvent(saveAddressMessage);
		return sm;
	}
	/*
	 * public StateMachine<OrderStates, OrderEvents> checkOut(Long orderId){
	 * StateMachine<OrderStates, OrderEvents> sm = this.build(orderId); //make sure
	 * that state is updated before this line came Message<OrderEvents>
	 * checkOutMessage = MessageBuilder.withPayload(OrderEvents.CHECKOUT)
	 * .setHeader(ORDER_ID_HEADER , orderId) .setHeader("isRegistered", false)
	 * .build();
	 * 
	 * sm.sendEvent(checkOutMessage); return sm; }
	 */
	
	/*
	 * public StateMachine<OrderStates, OrderEvents> saveInCartTemp(Long orderId){
	 * StateMachine<OrderStates, OrderEvents> sm = this.build(orderId); //make sure
	 * that state is updated before this line came Message<OrderEvents>
	 * saveInCartTempMessage =
	 * MessageBuilder.withPayload(OrderEvents.SAVE_TEMP_CART)
	 * .setHeader(ORDER_ID_HEADER , orderId) .setHeader("isRegistered", false)
	 * .build();
	 * 
	 * sm.sendEvent(saveInCartTempMessage); return sm; }
	 */
	
	private StateMachine<OrderStates, OrderEvents> build(Long orderId){
		Order order = this.orderRepository.getOne(orderId); //TODO
		String orderIdKey = Long.toString(order.getId());
		
		StateMachine<OrderStates, OrderEvents> sm  = this.factory.getStateMachine(orderIdKey);
		
		sm.stop(); //if previous cycle is not terminated yet
		
		System.out.println("SM1=====+=========");
		System.out.println(sm);
//		System.out.println();
		//System.out.println(sm);
		sm.getStateMachineAccessor()
		      .doWithAllRegions(new StateMachineFunction<StateMachineAccess<OrderStates,OrderEvents>>() {
				
				@Override
				public void apply(StateMachineAccess<OrderStates, OrderEvents> sma) {

					//to persist every state before stateChange
					sma.addStateMachineInterceptor(new StateMachineInterceptorAdapter<OrderStates, OrderEvents>(){
					
						@Override
						public void preStateChange(State<OrderStates, OrderEvents> state, Message<OrderEvents> message,
								Transition<OrderStates, OrderEvents> transition,
								StateMachine<OrderStates, OrderEvents> stateMachine) {
							System.out.println("fff");
							Optional.ofNullable(message).ifPresent( msg -> 
								
								Optional.ofNullable(Long.class.cast(msg.getHeaders().getOrDefault(ORDER_ID_HEADER, -1L))) //TODO
								    .ifPresent(orderId -> { //TODO orderId1
								    	Order order = orderRepository.getOne(orderId); //TODO
								    	order.setOrderState(state.getId());
								    	orderRepository.save(order);		   
							}));
							
						}
					});
					
					//DefaultStateMachineContext<OrderStates, OrderEvents>
					 sma.resetStateMachine(new DefaultStateMachineContext<OrderStates, OrderEvents>(order.getOrderState(), null, null, null));
				}
			});
		
		sm.start();
		return sm;
	}

	
	/*
	 * StateMachine<OrderStates, OrderEvents> pay(Long orderId, String
	 * paymentConfirmationNumber) { StateMachine<OrderStates, OrderEvents> sm =
	 * this.build(orderId);
	 * 
	 * Message<OrderEvents> paymentMessage =
	 * MessageBuilder.withPayload(OrderEvents.PAY) .setHeader(ORDER_ID_HEADER,
	 * orderId) .setHeader("paymentConfirmationNumber", paymentConfirmationNumber)
	 * .build();
	 * 
	 * sm.sendEvent(paymentMessage); // todo return sm; }
	 * 
	 * StateMachine<OrderStates, OrderEvents> fulfill(Long orderId) {
	 * StateMachine<OrderStates, OrderEvents> sm = this.build(orderId);
	 * Message<OrderEvents> fulfillmentMessage =
	 * MessageBuilder.withPayload(OrderEvents.FULFILL) .setHeader(ORDER_ID_HEADER,
	 * orderId) .build(); sm.sendEvent(fulfillmentMessage); return sm; }
	 * 
	 * private StateMachine<OrderStates, OrderEvents> build(Long orderId) { Order
	 * order = this.orderRepository.findOne(orderId); String orderIdKey =
	 * Long.toString(order.getId());
	 * 
	 * StateMachine<OrderStates, OrderEvents> sm =
	 * this.factory.getStateMachine(orderIdKey); sm.stop();
	 * sm.getStateMachineAccessor() .doWithAllRegions(sma -> {
	 * 
	 * sma.addStateMachineInterceptor(new
	 * StateMachineInterceptorAdapter<OrderStates, OrderEvents>() {
	 * 
	 * @Override public void preStateChange(State<OrderStates, OrderEvents> state,
	 * Message<OrderEvents> message, Transition<OrderStates, OrderEvents>
	 * transition, StateMachine<OrderStates, OrderEvents> stateMachine) {
	 * 
	 * Optional.ofNullable(message).ifPresent(msg -> {
	 * 
	 * Optional.ofNullable(Long.class.cast(msg.getHeaders().getOrDefault(
	 * ORDER_ID_HEADER, -1L))) .ifPresent(orderId1 -> { Order order1 =
	 * orderRepository.findOne(orderId1); order1.setOrderState(state.getId());
	 * orderRepository.save(order1); }); });
	 * 
	 * } }); sma.resetStateMachine(new
	 * DefaultStateMachineContext<>(order.getOrderState(), null, null, null)); });
	 * sm.start(); return sm; }
	 */
	
	public Order getOrderDtls(Long orderId) {
		return orderRepository.getOne(orderId);
		
	}
}
