/**
 * 
 */
package com.poc.shoppingcart.entity;

import java.io.Serializable;
import java.math.BigDecimal;

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
@Table(name = "CART_LIST")
public class CartList implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8270973826564855223L;
	
	@Id
	@Column (name = "CART_LIST_ID")
	private Integer cartListId;
	
	
	@Column (name = "CART_ID")
	private Integer cartId;
	
	@Column (name = "ITEM_ID")
	private Integer itemId;
	
	@Column (name = "ITEM_QTY")
	private Integer itemQty;
	
	@Column (name = "TOTAL_ITEM_PRICE")
	private BigDecimal totItemPrice;
	

	
}
