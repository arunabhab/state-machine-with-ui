/**
 * 
 */
package com.poc.shoppingcart.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.security.Timestamp;

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

@Entity
@Table(name = "ITEM")
public class Item implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8270973826564855223L;

	@Id
	@Column(name = "ITEM_ID")
	private Integer roleId;

	@Column(name = "ITEM_NAME")
	private String roleName;

	@Column(name = "IS_AVAILABLE")
	private Boolean isAvailable;

	@Column(name = "STOCK_QTY")
	private Integer stockQty;

	@Column(name = "price_per_unit")
	private BigDecimal pricePerUnit;

	public Item() {
	}

	public Item(Integer roleId, String roleName, Boolean isAvailable, Integer stockQty, BigDecimal pricePerUnit) {
		this.roleId = roleId;
		this.roleName = roleName;
		this.isAvailable = isAvailable;
		this.stockQty = stockQty;
		this.pricePerUnit = pricePerUnit;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Boolean getIsAvailable() {
		return isAvailable;
	}

	public void setIsAvailable(Boolean isAvailable) {
		this.isAvailable = isAvailable;
	}

	public Integer getStockQty() {
		return stockQty;
	}

	public void setStockQty(Integer stockQty) {
		this.stockQty = stockQty;
	}

	public BigDecimal getPricePerUnit() {
		return pricePerUnit;
	}

	public void setPricePerUnit(BigDecimal pricePerUnit) {
		this.pricePerUnit = pricePerUnit;
	}

	@Override
	public String toString() {
		return "Item [roleId=" + roleId + ", roleName=" + roleName + ", isAvailable=" + isAvailable + ", stockQty="
				+ stockQty + ", pricePerUnit=" + pricePerUnit + "]";
	}

}
