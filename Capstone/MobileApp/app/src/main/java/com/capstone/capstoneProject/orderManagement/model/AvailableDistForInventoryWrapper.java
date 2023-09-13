package com.capstone.capstoneProject.orderManagement.model;

import java.util.List;

public class AvailableDistForInventoryWrapper {

	private Integer statusCode;
	private String statusMessage;
	private List<AvailableDistForInventory> availableInventoryResponseList;
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
	public List<AvailableDistForInventory> getAvailableInventoryResponseList() {
		return availableInventoryResponseList;
	}
	public void setAvailableInventoryResponseList(List<AvailableDistForInventory> availableInventoryResponseList) {
		this.availableInventoryResponseList = availableInventoryResponseList;
	}
	@Override
	public String toString() {
		return "AvailableInventoryResponseWrapper [statusCode=" + statusCode + ", statusMessage=" + statusMessage
				+ ", availableInventoryResponseList=" + availableInventoryResponseList + "]";
	}
	
	
}
