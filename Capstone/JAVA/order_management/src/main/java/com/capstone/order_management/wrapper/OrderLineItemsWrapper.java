package com.capstone.order_management.wrapper;

import java.util.List;

import com.capstone.order_management.entities.pojos.OrderItemsPojo;

public class OrderLineItemsWrapper {

	private int statusCode;
	private String status;
	private List<OrderItemsPojo> message;
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
	public List<OrderItemsPojo> getMessage() {
		return message;
	}
	public void setMessage(List<OrderItemsPojo> message) {
		this.message = message;
	}
	@Override
	public String toString() {
		return "OrderLineItemsWrapper [statusCode=" + statusCode + ", status=" + status + ", message=" + message + "]";
	}
	
}
