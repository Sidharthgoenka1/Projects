package com.capstone.order_management.entities.pojos;

import java.util.List;

public class CreateOrderRequest {
	
	private Integer shopId;
	private String userType;
	private String comment;
	private List<CreateOrderRequestItems> createOrderRequestItemList;
	private Integer updatedBy;
	
	public Integer getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(Integer updatedBy) {
		this.updatedBy = updatedBy;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public Integer getShopId() {
		return shopId;
	}
	public void setShopId(Integer userId) {
		this.shopId = userId;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public List<CreateOrderRequestItems> getCreateOrderRequestItemList() {
		return createOrderRequestItemList;
	}
	public void setCreateOrderRequestItemList(List<CreateOrderRequestItems> createOrderRequestItemList) {
		this.createOrderRequestItemList = createOrderRequestItemList;
	}
	@Override
	public String toString() {
		return "CreateOrderRequest [shopId=" + shopId + ", userType=" + userType + ", comment=" + comment
				+ ", createOrderRequestItemList=" + createOrderRequestItemList + ", updatedBy=" + updatedBy + "]";
	}
	
	

}
