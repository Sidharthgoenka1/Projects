package com.capstone.capstoneProject.orderManagement.model;

import java.util.List;

public class CreateOrderRequest {
	
	private Integer shopId;
	private String userType;
	private String comment;
	private Integer workplaceId;
	private Integer updatedBy;
	private List<CreateOrderRequestItems> createOrderRequestItemList;

	public Integer getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(Integer updatedBy) {
		this.updatedBy = updatedBy;
	}

	@Override
	public String toString() {
		return "CreateOrderRequest{" +
				"shopId=" + shopId +
				", userType='" + userType + '\'' +
				", comment='" + comment + '\'' +
				", workplaceId=" + workplaceId +
				", updatedBy=" + updatedBy +
				", createOrderRequestItemList=" + createOrderRequestItemList +
				'}';
	}

	public Integer getWorkplaceId() {
		return workplaceId;
	}

	public void setWorkplaceId(Integer workplaceId) {
		this.workplaceId = workplaceId;
	}

	public Integer getShopId() {
		return shopId;
	}
	public void setShopId(Integer shopId) {
		this.shopId = shopId;
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
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}

}
