/**
 * 
 */
package com.poc.shoppingcart.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.Proxy;

import com.poc.shoppingcart.enums.OrderStates;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Sayanta.Ganguly
 *
 */

@Entity(name = "ORDERS")
@Data
@Proxy(lazy = false)
public class Order {
	@Id
	@Column(name = "ORDER_ID")
	private Long id;

	@Column(name = "DATE_TIME")
	private Date datetime;

	@Column(name = "STATE")
	private String state;

	
	
	public Order() {
	}

	public Order(Long id, Date d, OrderStates os) {
		this.datetime = d;
		this.setOrderState(os);
		this.id= id;  //new Long(123213);
	}

	
	
	public Date getDatetime() {
		return datetime;
	}

	public void setDatetime(Date datetime) {
		this.datetime = datetime;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	/*
	 * public Date getDatetime() { return datetime; }
	 * 
	 * public void setDatetime(Date datetime) { this.datetime = datetime; }
	 */

	public OrderStates getOrderState() {
		System.out.println("inside method getOrderState");
		return OrderStates.valueOf(this.state);
	}

	public void setOrderState(OrderStates s) {
		this.state = s.name();
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", datetime=" + datetime + ", state=" + state + "]";
	}

}
