package com.capstone.capstoneProject.composite.wrappers;

import java.util.List;

import com.capstone.capstoneProject.composite.pojos.OrderHistory;

public class OrderHistoryResponsePojo {
	
	Integer statusCode;
	String statusMessage;
	List<OrderHistory> orderDetailList;
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
	public List<OrderHistory> getOrderDetailList() {
		return orderDetailList;
	}
	public void setOrderDetailList(List<OrderHistory> orderDetailList) {
		this.orderDetailList = orderDetailList;
	}
	@Override
	public String toString() {
		return "OrderDetailsResponsePojo [statusCode=" + statusCode + ", statusMessage=" + statusMessage
				+ ", orderDetailList=" + orderDetailList + "]";
	}
	

}
