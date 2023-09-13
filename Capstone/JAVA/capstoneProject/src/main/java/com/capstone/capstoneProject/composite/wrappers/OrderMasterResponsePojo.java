package com.capstone.capstoneProject.composite.wrappers;

import java.util.List;

import com.capstone.capstoneProject.composite.pojos.OrderMaster;

public class OrderMasterResponsePojo {
	
	Integer statusCode;
	String statusMessage;
	OrderMaster orderMaster;
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
	public OrderMaster getOrderMaster() {
		return orderMaster;
	}
	public void setOrderMaster(OrderMaster orderMaster) {
		this.orderMaster = orderMaster;
	}
	@Override
	public String toString() {
		return "OrderMasterResponsePojo [statusCode=" + statusCode + ", statusMessage=" + statusMessage
				+ ", orderMaster=" + orderMaster + "]";
	}
	
	

}
