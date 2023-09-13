package com.capstone.order_management.entities.pojos;

import java.util.Date;

public class CreateOrderRequestItems {
	
	private Integer invId;
	private String invName;
	private Integer distId;
	private Date orderDate;
	private Date lastUpdateDate;
	private Integer statusId;
	private Integer quantity;
	private String orderDescription;
	
	
	public String getOrderDescription() {
		return orderDescription;
	}
	public void setOrderDescription(String orderDescription) {
		this.orderDescription = orderDescription;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public Integer getInvId() {
		return invId;
	}
	public void setInvId(Integer invId) {
		this.invId = invId;
	}
	public String getInvName() {
		return invName;
	}
	public void setInvName(String invName) {
		this.invName = invName;
	}
	public Integer getDistId() {
		return distId;
	}
	public void setDistId(Integer distId) {
		this.distId = distId;
	}
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	public Date getLastUpdateDate() {
		return lastUpdateDate;
	}
	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}
	public Integer getStatusId() {
		return statusId;
	}
	public void setStatusId(Integer statusId) {
		this.statusId = statusId;
	}
	
	@Override
	public String toString() {
		return "CreateOrderRequestItems [invId=" + invId + ", invName=" + invName + ", distId=" + distId
				+ ", orderDate=" + orderDate + ", lastUpdateDate=" + lastUpdateDate + ", statusId=" + statusId
				+ ", quantity=" + quantity + ", orderDescription=" + orderDescription + "]";
	}
	
	

}
