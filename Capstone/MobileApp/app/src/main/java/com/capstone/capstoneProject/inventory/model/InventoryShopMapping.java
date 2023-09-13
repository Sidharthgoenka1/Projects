package com.capstone.capstoneProject.inventory.model;

public class InventoryShopMapping {

	private Integer id;
	private Integer medId;
	private Integer shopId;
	private Integer quantity;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getMedId() {
		return medId;
	}
	public void setMedId(Integer medId) {
		this.medId = medId;
	}
	public Integer getShopId() {
		return shopId;
	}
	public void setShopId(Integer shopId) {
		this.shopId = shopId;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	@Override
	public String toString() {
		return "InventoryShopMapping [id=" + id + ", medId=" + medId + ", shopId=" + shopId + ", quantity=" + quantity
				+ "]";
	}
	
	
}