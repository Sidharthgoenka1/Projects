package com.capstone.capstoneProject.orderManagement.model;

public class UpdateItemsPojo {

	private Integer id;
	private Integer orderId;
	private Integer invId;
	private Integer distId;
	private Integer statusId;
	private String comments;
	private Integer updatedBy;
	private Integer quantity;

	public Integer getQuantity() {
		return quantity;
	}

	@Override
	public String toString() {
		return "UpdateItemsPojo{" +
				"id=" + id +
				", orderId=" + orderId +
				", invId=" + invId +
				", distId=" + distId +
				", statusId=" + statusId +
				", comments='" + comments + '\'' +
				", updatedBy=" + updatedBy +
				", quantity=" + quantity +
				'}';
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Integer getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Integer updatedBy) {
		this.updatedBy = updatedBy;
	}
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
	public Integer getDistId() {
		return distId;
	}
	public void setDistId(Integer distId) {
		this.distId = distId;
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
}
