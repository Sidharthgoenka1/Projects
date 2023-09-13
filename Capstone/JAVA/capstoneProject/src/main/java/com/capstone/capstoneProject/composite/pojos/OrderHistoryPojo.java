package com.capstone.capstoneProject.composite.pojos;

import java.util.Date;

public class OrderHistoryPojo {

	private Integer id;
	private Integer orderId;
	private String comment;
	private Date lastUpdateDate;
	private Integer updatedBy;
	private String userName;
	private Integer statusId;
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
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public Date getLastUpdateDate() {
		return lastUpdateDate;
	}
	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}
	public Integer getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(Integer updatedBy) {
		this.updatedBy = updatedBy;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Integer getStatusId() {
		return statusId;
	}
	public void setStatusId(Integer statusId) {
		this.statusId = statusId;
	}
	@Override
	public String toString() {
		return "OrderHistoryPojo [id=" + id + ", orderId=" + orderId + ", comment=" + comment + ", lastUpdateDate="
				+ lastUpdateDate + ", updatedBy=" + updatedBy + ", userName=" + userName + ", statusId=" + statusId
				+ "]";
	}
	
}
