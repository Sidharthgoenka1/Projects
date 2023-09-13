package com.capstone.capstoneProject.inventory.model;

import java.util.List;

public class InventoryDistributorMappingResponsePojo {
	
	Integer statusCode;
	String statusMessage;
	List<InventoryDistMapping> inventoryDistMappingList;
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
	public List<InventoryDistMapping> getInventoryDistMappingList() {
		return inventoryDistMappingList;
	}
	public void setInventoryDistMappingList(List<InventoryDistMapping> inventoryDistMappingList) {
		this.inventoryDistMappingList = inventoryDistMappingList;
	}
	@Override
	public String toString() {
		return "InventoryDistributorMappingResponsePojo [statusCode=" + statusCode + ", statusMessage=" + statusMessage
				+ ", inventoryDistMappingList=" + inventoryDistMappingList + "]";
	}

}