package com.capstone.order_management.entities.pojos;

public class UpdateOrderRequest {

	private Integer orderId;
	private Integer statusId;
	private Integer userId;
	private String comment;
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public Integer getStatusId() {
		return statusId;
	}
	public void setStatusId(Integer statusId) {
		this.statusId = statusId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	@Override
	public String toString() {
		return "UpdateOrderRequest [orderId=" + orderId + ", statusId=" + statusId + ", userId=" + userId + ", comment="
				+ comment + "]";
	}
	
	
}
