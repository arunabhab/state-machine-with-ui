package com.poc.shoppingcart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.EnableStateMachine;

import com.poc.shoppingcart.enums.Events;
import com.poc.shoppingcart.enums.States;

/**
 * @author ArunabhaB
 *
 */

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.poc.shoppingcart.repository")
@EntityScan(basePackages = "com.poc.shoppingcart.entity")
public class ShoppingCartApplication extends SpringBootServletInitializer
/* implements CommandLineRunner */ {

	@Autowired
	private StateMachine<States, Events> myStateMachine;
	
	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(ShoppingCartApplication.class);
    }
	
	public static void main(String[] args) {
		SpringApplication.run(ShoppingCartApplication.class, args);
	}

	/*
	 * @Override public void run(String... args) throws Exception {
	 * myStateMachine.sendEvent(Events.E1); myStateMachine.sendEvent(Events.E2); }
	 */
	
}
