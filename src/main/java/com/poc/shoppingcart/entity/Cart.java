/**
 * 
 */
package com.poc.shoppingcart.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author ArunabhaB
 *
 */

@Getter
@Setter
@ToString
@Entity
@Table(name = "CART")
public class Cart implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8270973826564855223L;
	
	@Id
	@Column (name = "CART_ID")
	private Integer cartId;
	
	@Column (name = "CUST_ID")
	private Integer custId;
	
	@Column (name = "CART_STATUS")
	private String cartStatus;
	
	@Column (name = "TOTAL_AMOUNT_ORDER")
	private BigDecimal totOrderAmt;
	
	
	@Column (name = "CRTN_TS")
	private Timestamp crtnTs;
	
	
	

	
}
