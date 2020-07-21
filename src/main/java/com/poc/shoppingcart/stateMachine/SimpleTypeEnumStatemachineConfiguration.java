/**
 * 
 */
package com.poc.shoppingcart.stateMachine;

import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;
import org.springframework.statemachine.config.EnableStateMachineFactory;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.guard.Guard;
import org.springframework.statemachine.listener.StateMachineListenerAdapter;
import org.springframework.statemachine.state.State;
import org.springframework.util.ObjectUtils;

import com.poc.shoppingcart.enums.OrderEvents;
import com.poc.shoppingcart.enums.OrderStates;
import com.poc.shoppingcart.enums.OrderTypeEvents;
import com.poc.shoppingcart.enums.OrderTypeStates;

import lombok.extern.java.Log;

/**
 * @author Sayanta.Ganguly
 *
 */

/*
 * @Log
 * 
 * @Configuration
 * 
 * @EnableStateMachineFactory
 */
public class SimpleTypeEnumStatemachineConfiguration {
	/*
	 * 
	 * public void configure(StateMachineTransitionConfigurer<OrderTypeStates,
	 * OrderTypeEvents> transitions) throws Exception { transitions
	 * .withExternal().source(OrderStates.SUBMITTED).target(OrderStates.PAID).event(
	 * OrderEvents.PAY) .and()
	 * //.withExternal().source(OrderStates.PAID).target(OrderStates.FULFILLED).
	 * event(OrderEvents.FULFILL) //.and()
	 * 
	 * .withExternal().source(OrderStates.PAID).target(OrderStates.FORK_REGISTRATION
	 * ).event(OrderEvents.CHECKOUT) .and()
	 * 
	 * .withChoice().source(OrderStates.CHOICE_REGISTRATION).first(OrderStates.
	 * REGISTERED, guard()).last(OrderStates.UNREGISTERED) .and()
	 * .withExternal().source(OrderStates.UNREGISTERED).target(OrderStates.
	 * REGISTERED).event(OrderEvents.REGISTER) .and()
	 * .withExternal().source(OrderStates.REGISTERED).target(OrderStates.
	 * CART_SAVED_IN_TEMP).event(OrderEvents.SAVE_TEMP_CART) .and()
	 * .withExternal().source(OrderStates.UNREGISTERED).target(OrderStates.
	 * REGISTERED).event(OrderEvents.REGISTER) .and()
	 * .withExternal().source(OrderStates.SUBMITTED).target(OrderStates.CANCELLED).
	 * event(OrderEvents.CANCEL) .and()
	 * .withExternal().source(OrderStates.PAID).target(OrderStates.CANCELLED).event(
	 * OrderEvents.CANCEL);
	 * .withExternal().source(OrderTypeStates.SUBMITTED_TYPE).target(OrderTypeStates
	 * .CART_SAVED_TYPE).event(OrderTypeEvents.SAVE_CART_TYPE) .and()
	 * .withExternal().source(OrderTypeStates.CART_SAVED_TYPE).target(
	 * OrderTypeStates.CART_RETRIEVED_TYPE).event(OrderTypeEvents.RETRIEVE_TYPE)
	 * .and() .withExternal().source(OrderTypeStates.CART_RETRIEVED_TYPE).target(
	 * OrderTypeStates.CART_UPDATED_TYPE).event(OrderTypeEvents.UPDATE_CART_TYPE)
	 * .and() .withExternal().source(OrderTypeStates.CART_SAVED_TYPE).target(
	 * OrderTypeStates.PAID_TYPE).event(OrderTypeEvents.PAY_TYPE) .and()
	 * .withExternal().source(OrderTypeStates.CART_UPDATED_TYPE).target(
	 * OrderTypeStates.PAID_TYPE).event(OrderTypeEvents.PAY_TYPE) .and()
	 * .withExternal().source(OrderTypeStates.CART_RETRIEVED_TYPE).target(
	 * OrderTypeStates.PAID_TYPE).event(OrderTypeEvents.PAY_TYPE) .and()
	 * .withExternal().source(OrderTypeStates.CART_SAVED_TYPE).target(
	 * OrderTypeStates.CANCELLED_TYPE).event(OrderTypeEvents.CANCEL_TYPE) .and()
	 * .withExternal().source(OrderTypeStates.CART_RETRIEVED_TYPE).target(
	 * OrderTypeStates.CANCELLED_TYPE).event(OrderTypeEvents.CANCEL_TYPE) .and()
	 * .withExternal().source(OrderTypeStates.CART_UPDATED_TYPE).target(
	 * OrderTypeStates.CANCELLED_TYPE).event(OrderTypeEvents.CANCEL_TYPE) .and()
	 * .withExternal().source(OrderTypeStates.PAID_TYPE).target(OrderTypeStates.
	 * CANCELLED_TYPE).event(OrderTypeEvents.CANCEL_TYPE); //.and()
	 * .withExternal().source(OrderStates.CART_SAVED1).target(OrderStates.
	 * CART_RETRIEVED1).event(OrderEvents.RETRIEVE1) .and()
	 * .withExternal().source(OrderStates.CART_RETRIEVED1).target(OrderStates.
	 * CART_UPDATED1).event(OrderEvents.UPDATE_CART1) .and()
	 * .withExternal().source(OrderStates.CART_SAVED1).target(OrderStates.PAID1).
	 * event(OrderEvents.PAY1) .and()
	 * .withExternal().source(OrderStates.CART_UPDATED1).target(OrderStates.PAID1).
	 * event(OrderEvents.PAY1) .and()
	 * .withExternal().source(OrderStates.CART_RETRIEVED1).target(OrderStates.PAID1)
	 * .event(OrderEvents.PAY1); }
	 * 
	 * public void configure(StateMachineStateConfigurer<OrderTypeStates,
	 * OrderTypeEvents> states) throws Exception {
	 * states.withStates().initial(OrderTypeStates.SUBMITTED_TYPE)
	 * .stateEntry(OrderTypeStates.SUBMITTED_TYPE, new Action<OrdertypeStates,
	 * OrderTypeEvents>() {
	 * 
	 * public void executeThis(StateContext<OrderTypeStates, OrderTypeEvents>
	 * context) { System.out.println("Entering submitted state"); Long orderId =
	 * Long.class
	 * .cast(context.getExtendedState().getVariables().getOrDefault("orderId",
	 * -1L)); System.out.println("Order id is : " + orderId); } })
	 * 
	 * .stateEntry(OrderStates.SUBMITTED, context -> { Long orderId =
	 * Long.class.cast(context.getExtendedState().getVariables().getOrDefault(
	 * "orderId", -1L)); log.info("orderId is " + orderId + ".");
	 * log.info("entering submitted state!"); })
	 * 
	 * .state(OrderTypeStates.CART_SAVED_TYPE)
	 * .state(OrderTypeStates.CART_RETRIEVED_TYPE)
	 * .state(OrderTypeStates.CART_UPDATED_TYPE) .state(OrderTypeStates.PAID_TYPE)
	 * 
	 * .fork(OrderStates.FORK_REGISTRATION) //TODO
	 * .state(OrderStates.CHECK_REGISTRATION) .join(OrderStates.JOIN_REGISTRATION)
	 * .choice(OrderStates.CHOICE_REGISTRATION) .and() .withStates()
	 * .parent(OrderStates.TASK) .initial(OrderStates.UNREGISTERED)
	 * .end(OrderStates.REGISTERED) .and() .withStates()
	 * .parent(OrderStates.CHECK_REGISTRATION) .initial(OrderStates.REGISTERED)
	 * .end(OrderStates.REGISTERED)
	 * 
	 * // .choice(OrderStates.CHOICE_REGISTRATION).end(OrderStates.FULFILLED).end(
	 * OrderStates.CANCELLED); .end(OrderTypeStates.CANCELLED_TYPE); }
	 * 
	 * 
	 * 
	 * public void configure(StateMachineConfigurationConfigurer<OrderTypeStates,
	 * OrderTypeEvents> config) throws Exception {
	 * 
	 * StateMachineListenerAdapter<OrderStates, OrderEvents> adapter = new
	 * StateMachineListenerAdapter<OrderStates, OrderEvents>() {
	 * 
	 * @Override public void stateChanged(State<OrderStates, OrderEvents> from,
	 * State<OrderStates, OrderEvents> to) { //
	 * log.info(String.format("stateChanged(from: %s, to: %s)", from + "", to + //
	 * "")); } }; config.withConfiguration().autoStartup(false).listener(adapter); }
	 * 
	 * 
	 */

}
