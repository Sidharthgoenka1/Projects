package com.capstone.capstoneProject.inventory.model;

public class InventoryDistMapping {
	private Integer id;
	
	private Integer medId;
	private Integer distId;
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
	public Integer getDistId() {
		return distId;
	}
	public void setDistId(Integer distId) {
		this.distId = distId;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	@Override
	public String toString() {
		return "InventoryDistMapping [id=" + id + ", medId=" + medId + ", distId=" + distId + ", quantity=" + quantity
				+ "]";
	}
	
	
	
}