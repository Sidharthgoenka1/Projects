package com.capstone.capstoneProject.orderManagement.model;

import java.util.Date;

public class OrderDetailsItemHistoryPojo {

	private Integer id;
	private Integer orderId;
	private Integer invId;
	private Integer statusId;
	private String comments;
	private Integer updatedBy;
	private String username;
	private Date updateDate;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public Integer getInvId() {
		return invId;
	}
	public void setInvId(Integer invId) {
		this.invId = invId;
	}
	public Integer getStatusId() {
		return statusId;
	}
	public void setStatusId(Integer statusId) {
		this.statusId = statusId;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public Integer getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(Integer updatedBy) {
		this.updatedBy = updatedBy;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	@Override
	public String toString() {
		return "OrderDetailsItemHistoryPojo [id=" + id + ", orderId=" + orderId + ", invId=" + invId + ", statusId="
				+ statusId + ", comments=" + comments + ", updatedBy=" + updatedBy + ", username=" + username
				+ ", updateDate=" + updateDate + "]";
	}
	
	
}