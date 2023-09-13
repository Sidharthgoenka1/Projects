package com.capstone.capstoneProject.dashboard.model;

import java.util.List;

public class InventoryMasterResponsePojo {
	
	Integer statusCode;
	String statusMessage;
	List<InventoryMaster> inventoryMasterList;
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
	public List<InventoryMaster> getInventoryMasterList() {
		return inventoryMasterList;
	}
	public void setInventoryMasterList(List<InventoryMaster> inventoryMasterList) {
		this.inventoryMasterList = inventoryMasterList;
	}
	@Override
	public String toString() {
		return "InventoryResponsePojo [statusCode=" + statusCode + ", statusMessage=" + statusMessage
				+ ", inventoryMasterList=" + inventoryMasterList + "]";
	}

}