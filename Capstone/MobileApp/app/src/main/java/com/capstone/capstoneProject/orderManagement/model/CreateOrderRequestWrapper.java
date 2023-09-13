package com.capstone.capstoneProject.orderManagement.model;


public class CreateOrderRequestWrapper {
	
	private Integer statusCode;
	private String status;
	private CreateOrderRequest message;
	public Integer getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public CreateOrderRequest getMessage() {
		return message;
	}
	public void setMessage(CreateOrderRequest message) {
		this.message = message;
	}
	@Override
	public String toString() {
		return "CreateOrderRequestWrapper [statusCode=" + statusCode + ", status=" + status + ", message=" + message
				+ "]";
	}
}
