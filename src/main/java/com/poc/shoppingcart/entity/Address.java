/**
 * 
 */
package com.poc.shoppingcart.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Sayanta.Ganguly
 *
 */

@Entity
@Table(name = "ADDRESS")
public class Address implements Serializable{

	private static final long serialVersionUID = -8270973826564855223L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column (name = "ADDRESS_ID")
	private Integer addressId;
	
	@Column (name = "ADDRESS_PIN")
	private String address;

	@Column (name = "CUST_ID")
	private String custId;
	
	public Address() {
		
	}

	

	public Address(Integer addressId, String address, String custId) {
		this.addressId = addressId;
		this.address = address;
		this.custId = custId;
	}



	public Integer getAddressId() {
		return addressId;
	}

	public void setAddressId(Integer addressId) {
		this.addressId = addressId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}



	/**
	 * @return the custId
	 */
	public String getCustId() {
		return custId;
	}



	/**
	 * @param custId the custId to set
	 */
	public void setCustId(String custId) {
		this.custId = custId;
	}

	
	
}
