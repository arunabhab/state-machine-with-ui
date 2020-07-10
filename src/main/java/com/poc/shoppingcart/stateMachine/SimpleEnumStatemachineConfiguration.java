package com.poc.shoppingcart.stateMachine;

import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;
import org.springframework.statemachine.config.EnableStateMachineFactory;
import org.springframework.statemachine.config.StateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.guard.Guard;
import org.springframework.statemachine.listener.StateMachineListenerAdapter;
import org.springframework.statemachine.state.State;
import org.springframework.util.ObjectUtils;

import com.poc.shoppingcart.enums.OrderEvents;
import com.poc.shoppingcart.enums.OrderStates;

import lombok.extern.java.Log;

/**
 * @author Sayanta.Ganguly
 *
 */

@Log
@Configuration
@EnableStateMachineFactory
public class SimpleEnumStatemachineConfiguration extends StateMachineConfigurerAdapter<OrderStates, OrderEvents> {

	@Override
	public void configure(StateMachineTransitionConfigurer<OrderStates, OrderEvents> transitions) throws Exception {
		transitions
				/*.withExternal().source(OrderStates.SUBMITTED).target(OrderStates.PAID).event(OrderEvents.PAY)
				.and()
				//.withExternal().source(OrderStates.PAID).target(OrderStates.FULFILLED).event(OrderEvents.FULFILL)
				//.and()
				
				 * .withExternal().source(OrderStates.PAID).target(OrderStates.FORK_REGISTRATION
				 * ).event(OrderEvents.CHECKOUT) .and()
				 
				.withChoice().source(OrderStates.CHOICE_REGISTRATION).first(OrderStates.REGISTERED, guard()).last(OrderStates.UNREGISTERED)
				.and()
				.withExternal().source(OrderStates.UNREGISTERED).target(OrderStates.REGISTERED).event(OrderEvents.REGISTER)
				.and()
				.withExternal().source(OrderStates.REGISTERED).target(OrderStates.CART_SAVED_IN_TEMP).event(OrderEvents.SAVE_TEMP_CART)
				.and()
				.withExternal().source(OrderStates.UNREGISTERED).target(OrderStates.REGISTERED).event(OrderEvents.REGISTER)
				.and()
				.withExternal().source(OrderStates.SUBMITTED).target(OrderStates.CANCELLED).event(OrderEvents.CANCEL)
				.and()
				.withExternal().source(OrderStates.PAID).target(OrderStates.CANCELLED).event(OrderEvents.CANCEL);*/
		
		.withExternal().source(OrderStates.SUBMITTED).target(OrderStates.CART_SAVED).event(OrderEvents.SAVE_CART)
		.and()
		.withExternal().source(OrderStates.CART_SAVED).target(OrderStates.CART_RETRIEVED).event(OrderEvents.RETRIEVE)
		.and()
		.withExternal().source(OrderStates.CART_RETRIEVED).target(OrderStates.CART_UPDATED).event(OrderEvents.UPDATE_CART)
		.and()
		.withExternal().source(OrderStates.CART_SAVED).target(OrderStates.PAID).event(OrderEvents.PAY)
		.and()
		.withExternal().source(OrderStates.CART_UPDATED).target(OrderStates.PAID).event(OrderEvents.PAY)
		.and()
		.withExternal().source(OrderStates.CART_RETRIEVED).target(OrderStates.PAID).event(OrderEvents.PAY)
		.and()
		.withExternal().source(OrderStates.CART_SAVED).target(OrderStates.CANCELLED).event(OrderEvents.CANCEL)
		.and()
		.withExternal().source(OrderStates.CART_RETRIEVED).target(OrderStates.CANCELLED).event(OrderEvents.CANCEL)
		.and()
		.withExternal().source(OrderStates.CART_UPDATED).target(OrderStates.CANCELLED).event(OrderEvents.CANCEL)
		.and()
		.withExternal().source(OrderStates.PAID).target(OrderStates.CANCELLED).event(OrderEvents.CANCEL);
	}

	@Override
	public void configure(StateMachineStateConfigurer<OrderStates, OrderEvents> states) throws Exception {
		states.withStates().initial(OrderStates.SUBMITTED)
				.stateEntry(OrderStates.SUBMITTED, new Action<OrderStates, OrderEvents>() {

					@Override
					public void execute(StateContext<OrderStates, OrderEvents> context) {
						System.out.println("Entering submitted state");
						Long orderId = Long.class
								.cast(context.getExtendedState().getVariables().getOrDefault("orderId", -1L));
						System.out.println("Order id is : " + orderId);
					}
				})
				/*
				 * .stateEntry(OrderStates.SUBMITTED, context -> { Long orderId =
				 * Long.class.cast(context.getExtendedState().getVariables().getOrDefault(
				 * "orderId", -1L)); log.info("orderId is " + orderId + ".");
				 * log.info("entering submitted state!"); })
				 */
				.state(OrderStates.CART_SAVED)
				.state(OrderStates.CART_RETRIEVED)
				.state(OrderStates.CART_UPDATED)
				.state(OrderStates.PAID)
				/*
				 * .fork(OrderStates.FORK_REGISTRATION) //TODO
				 * .state(OrderStates.CHECK_REGISTRATION) .join(OrderStates.JOIN_REGISTRATION)
				 * .choice(OrderStates.CHOICE_REGISTRATION) .and() .withStates()
				 * .parent(OrderStates.TASK) .initial(OrderStates.UNREGISTERED)
				 * .end(OrderStates.REGISTERED) .and() .withStates()
				 * .parent(OrderStates.CHECK_REGISTRATION) .initial(OrderStates.REGISTERED)
				 * .end(OrderStates.REGISTERED)
				 */
			//	.choice(OrderStates.CHOICE_REGISTRATION).end(OrderStates.FULFILLED).end(OrderStates.CANCELLED);
				.end(OrderStates.CANCELLED);
	}

	@Override
	public void configure(StateMachineConfigurationConfigurer<OrderStates, OrderEvents> config) throws Exception {

		StateMachineListenerAdapter<OrderStates, OrderEvents> adapter = new StateMachineListenerAdapter<OrderStates, OrderEvents>() {
			@Override
			public void stateChanged(State<OrderStates, OrderEvents> from, State<OrderStates, OrderEvents> to) {
				// log.info(String.format("stateChanged(from: %s, to: %s)", from + "", to +
				// ""));
			}
		};
		config.withConfiguration().autoStartup(false).listener(adapter);
	}

	@Bean
	public Guard<OrderStates, OrderEvents> guard() {
		return new Guard<OrderStates, OrderEvents>() {

			@Override
			public boolean evaluate(StateContext<OrderStates, OrderEvents> context) {
				Map<Object, Object> variables = context.getExtendedState().getVariables();
				return (ObjectUtils.nullSafeEquals(variables.get("isRegistered"), true));

				/*
				 * context.getStateMachine().sendEvent(Events.CONTINUE); } else {
				 * context.getStateMachine().sendEvent(Events.FALLBACK); }
				 */
			}
		};
	}
}
