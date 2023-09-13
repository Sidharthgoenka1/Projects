package com.capstone.capstoneProject.composite.pojos;

import java.util.Date;
import java.util.List;


public class OrdersDataPojo {
	
	private Integer orderId;
	private Integer shopId;
	private String shopName;
	private String shopAddress;
	private Integer statusId;
	private Date orderDate;
	
	public String getShopName() {
		return shopName;
	}
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	public String getShopAddress() {
		return shopAddress;
	}
	public void setShopAddress(String shopAddress) {
		this.shopAddress = shopAddress;
	}
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public Integer getShopId() {
		return shopId;
	}
	public void setShopId(Integer shopId) {
		this.shopId = shopId;
	}
	public Integer getStatusId() {
		return statusId;
	}
	public void setStatusId(Integer statusId) {
		this.statusId = statusId;
	}
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	@Override
	public String toString() {
		return "OrdersDataPojo [orderId=" + orderId + ", shopId=" + shopId + ", shopName=" + shopName + ", shopAddress="
				+ shopAddress + ", statusId=" + statusId + ", orderDate=" + orderDate + ", items=" + items
				+ ", history=" + history + "]";
	}
	
	private List<OrderItemsPojo> items;
	private List<OrderHistoryPojo> history;
	
	public List<OrderItemsPojo> getItems() {
		return items;
	}
	public void setItems(List<OrderItemsPojo> items) {
		this.items = items;
	}
	public List<OrderHistoryPojo> getHistory() {
		return history;
	}
	public void setHistory(List<OrderHistoryPojo> history) {
		this.history = history;
	}
	
}
