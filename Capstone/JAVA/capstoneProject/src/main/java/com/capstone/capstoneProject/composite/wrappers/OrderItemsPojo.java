package com.capstone.capstoneProject.composite.wrappers;

public class OrderItemsPojo {

	private Integer id;
	private Integer orderId;
	private Integer invId;
	private String invName;
	private Integer quantity;
	private Integer distId;
	private String distName;
	private Integer wId;
	private String wName;
	private String wAddress;
	private Integer statusId;
	
	public Integer getStatusId() {
		return statusId;
	}
	public void setStatusId(Integer statusId) {
		this.statusId = statusId;
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
	public String getInvName() {
		return invName;
	}
	public void setInvName(String invName) {
		this.invName = invName;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public Integer getDistId() {
		return distId;
	}
	public void setDistId(Integer distId) {
		this.distId = distId;
	}
	public String getDistName() {
		return distName;
	}
	public void setDistName(String distName) {
		this.distName = distName;
	}
	public Integer getwId() {
		return wId;
	}
	public void setwId(Integer wId) {
		this.wId = wId;
	}
	public String getwName() {
		return wName;
	}
	public void setwName(String wName) {
		this.wName = wName;
	}
	public String getwAddress() {
		return wAddress;
	}
	public void setwAddress(String wAddress) {
		this.wAddress = wAddress;
	}
	@Override
	public String toString() {
		return "OrderItemsPojo [id=" + id + ", orderId=" + orderId + ", invId=" + invId + ", invName=" + invName
				+ ", quantity=" + quantity + ", distId=" + distId + ", distName=" + distName + ", wId=" + wId
				+ ", wName=" + wName + ", wAddress=" + wAddress + ", statusId=" + statusId + "]";
	}
	
	
}
