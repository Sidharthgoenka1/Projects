package com.capstone.capstoneProject.composite.wrappers;

import java.util.List;

import com.capstone.capstoneProject.composite.pojos.AvailableDistForInventory;

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
