package com.capstone.capstoneProject.orderManagement.model;

import java.util.Date;

public class CreateOrderRequestItems {
	
	private Integer invId;
	private String invName;
	private Integer distId;
	private String distName;
	private Date orderDate;
	private Date lastUpdateDate;
	private Integer statusId;
	private String orderDescription;
	private Integer quantity;

	@Override
	public String toString() {
		return "CreateOrderRequestItems{" +
				"invId=" + invId +
				", invName='" + invName + '\'' +
				", distId=" + distId +
				", distName='" + distName + '\'' +
				", orderDate=" + orderDate +
				", lastUpdateDate=" + lastUpdateDate +
				", statusId=" + statusId +
				", orderDescription='" + orderDescription + '\'' +
				", quantity=" + quantity +
				'}';
	}

	public String getDistName() {
		return distName;
	}

	public void setDistName(String distName) {
		this.distName = distName;
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
	public String getOrderDescription() {
		return orderDescription;
	}
	public void setOrderDescription(String orderDescription) {
		this.orderDescription = orderDescription;
	}


}
