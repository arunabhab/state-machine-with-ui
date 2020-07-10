package com.poc.shoppingcart.entity;

public class CartDAO {
	private String cartId;
	private String orderId;
	private String status;
	
	
	
	public CartDAO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CartDAO(String cartId, Long orderId, String status) {
		this.cartId = cartId;
		this.orderId = orderId.toString();
		this.status=status;
	}
	
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCartId() {
		return cartId;
	}
	public void setCartId(String cartId) {
		this.cartId = cartId;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(Long orderId) {
		this.orderId = orderId.toString();
	}
	@Override
	public String toString() {
		return "CartDAO [cartId=" + cartId + ", orderId=" + orderId + ", status=" + status + "]";
	}

	
	
	

}
