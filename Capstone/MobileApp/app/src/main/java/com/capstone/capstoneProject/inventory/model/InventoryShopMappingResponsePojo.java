package com.capstone.capstoneProject.inventory.model;

import java.util.List;

public class InventoryShopMappingResponsePojo {
	
	Integer statusCode;
	String statusMessage;
	List<InventoryShopMapping> inventoryShopMappingList;
	public Integer getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}
	public String getStatusMessage() {
		return statusMessage;
	}
	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}
	public List<InventoryShopMapping> getInventoryShopMappingList() {
		return inventoryShopMappingList;
	}
	public void setInventoryShopMappingList(List<InventoryShopMapping> inventoryShopMappingList) {
		this.inventoryShopMappingList = inventoryShopMappingList;
	}
	@Override
	public String toString() {
		return "InventoryShopMappingResponsePojo [statusCode=" + statusCode + ", statusMessage=" + statusMessage
				+ ", inventoryShopMappingList=" + inventoryShopMappingList + "]";
	}
	
	

}
