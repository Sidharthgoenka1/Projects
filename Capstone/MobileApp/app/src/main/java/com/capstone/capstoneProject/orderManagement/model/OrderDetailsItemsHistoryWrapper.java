package com.capstone.capstoneProject.orderManagement.model;

import java.util.List;

public class OrderDetailsItemsHistoryWrapper {

	private int statusCode;
	private String status;
	private List<OrderDetailsItemHistoryPojo> message;
	public int getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public List<OrderDetailsItemHistoryPojo> getMessage() {
		return message;
	}
	public void setMessage(List<OrderDetailsItemHistoryPojo> message) {
		this.message = message;
	}
	@Override
	public String toString() {
		return "OrderDetailsItemsHistoryWrapper [statusCode=" + statusCode + ", status=" + status + ", message="
				+ message + "]";
	}
	
}
