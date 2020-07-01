/**
 * 
 */
package com.poc.shoppingcart.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author ArunabhaB
 *
 */


@Entity
@Table(name = "CART_TEMP")
public class CartTemp implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8270973826564855223L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column (name = "CART_ID")
	private Integer cartId;
	
	@Column (name = "CUST_ID")
	private Integer custId;
	
	@Column (name = "ITEM_LIST")
	private String itemList;
	
	@Column (name = "CRTN_TS")
	private Timestamp crtnTs;

	public CartTemp(Integer cartId, Integer custId, String itemList, Timestamp crtnTs) {
		super();
		this.cartId = cartId;
		this.custId = custId;
		this.itemList = itemList;
		this.crtnTs = crtnTs;
	}
	
	public CartTemp(Integer custId, String itemList, Timestamp crtnTs) {
		this.custId = custId;
		this.itemList = itemList;
		this.crtnTs = crtnTs;
	}

	public CartTemp() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "CartTemp [cartId=" + cartId + ", custId=" + custId + ", itemList=" + itemList + ", crtnTs=" + crtnTs
				+ "]";
	}

	public Integer getCartId() {
		return cartId;
	}

	public void setCartId(Integer cartId) {
		this.cartId = cartId;
	}

	public Integer getCustId() {
		return custId;
	}

	public void setCustId(Integer custId) {
		this.custId = custId;
	}

	public String getItemList() {
		return itemList;
	}

	public void setItemList(String itemList) {
		this.itemList = itemList;
	}

	public Timestamp getCrtnTs() {
		return crtnTs;
	}

	public void setCrtnTs(Timestamp crtnTs) {
		this.crtnTs = crtnTs;
	}
	
	
	

	
}
