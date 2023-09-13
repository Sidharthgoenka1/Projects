package com.capstone.capstoneProject.orderManagement.model;

import java.util.List;


public class OrderDataWrapper {

	private int statusCode;
	private String status;
	private List<OrdersDataPojo> message;
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
	public List<OrdersDataPojo> getMessage() {
		return message;
	}
	public void setMessage(List<OrdersDataPojo> message) {
		this.message = message;
	}
	@Override
	public String toString() {
		return "OrderDataWrapper [statusCode=" + statusCode + ", status=" + status + ", message=" + message + "]";
	}
	
}
