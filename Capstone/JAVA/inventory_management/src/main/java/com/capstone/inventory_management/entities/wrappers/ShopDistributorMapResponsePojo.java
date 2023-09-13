package com.capstone.inventory_management.entities.wrappers;

import java.util.List;

import com.capstone.inventory_management.entities.ShopDistributorMap;

public class ShopDistributorMapResponsePojo {
	
	Integer statusCode;
	String statusMessage;
	List<ShopDistributorMap> shopDistributorMapList;
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
	public List<ShopDistributorMap> getShopDistributorMapList() {
		return shopDistributorMapList;
	}
	public void setShopDistributorMapList(List<ShopDistributorMap> shopDistributorMapList) {
		this.shopDistributorMapList = shopDistributorMapList;
	}
	@Override
	public String toString() {
		return "ShopDistributorMapResponsePojo [statusCode=" + statusCode + ", statusMessage=" + statusMessage
				+ ", shopDistributorMapList=" + shopDistributorMapList + "]";
	}
	

}
