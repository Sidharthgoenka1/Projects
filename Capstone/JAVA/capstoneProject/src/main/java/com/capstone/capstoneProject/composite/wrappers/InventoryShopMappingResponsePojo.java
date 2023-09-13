package com.capstone.capstoneProject.composite.wrappers;

import java.util.List;

import com.capstone.capstoneProject.composite.pojos.InventoryShopMapping;

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
