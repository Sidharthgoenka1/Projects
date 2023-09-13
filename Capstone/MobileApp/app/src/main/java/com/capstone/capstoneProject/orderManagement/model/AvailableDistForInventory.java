package com.capstone.capstoneProject.orderManagement.model;

public class AvailableDistForInventory {

	private String invId;
	private String invName;
	private String invDesc;
	private String distId;
	private String distName;
	private String distAddress;
	private String distContact;
	private String wAddress;
	private String wName;
	private String wContact;
	private Integer wId;
	private String email;
	private Integer quantity;

	@Override
	public String toString() {
		return "AvailableDistForInventory{" +
				"invId='" + invId + '\'' +
				", invName='" + invName + '\'' +
				", invDesc='" + invDesc + '\'' +
				", distId='" + distId + '\'' +
				", distName='" + distName + '\'' +
				", distAddress='" + distAddress + '\'' +
				", distContact='" + distContact + '\'' +
				", wAddress='" + wAddress + '\'' +
				", wName='" + wName + '\'' +
				", wContact='" + wContact + '\'' +
				", wId=" + wId +
				", email='" + email + '\'' +
				", quantity=" + quantity +
				'}';
	}

	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getwAddress() {
		return wAddress;
	}
	public void setwAddress(String wAddress) {
		this.wAddress = wAddress;
	}
	public String getwName() {
		return wName;
	}
	public void setwName(String wName) {
		this.wName = wName;
	}
	public String getwContact() {
		return wContact;
	}
	public void setwContact(String wContact) {
		this.wContact = wContact;
	}
	public Integer getwId() {
		return wId;
	}
	public void setwId(Integer wId) {
		this.wId = wId;
	}
	public String getInvId() {
		return invId;
	}
	public void setInvId(String invId) {
		this.invId = invId;
	}
	public String getInvName() {
		return invName;
	}
	public void setInvName(String invName) {
		this.invName = invName;
	}
	public String getInvDesc() {
		return invDesc;
	}
	public void setInvDesc(String invDesc) {
		this.invDesc = invDesc;
	}
	public String getDistId() {
		return distId;
	}
	public void setDistId(String distId) {
		this.distId = distId;
	}
	public String getDistName() {
		return distName;
	}
	public void setDistName(String distName) {
		this.distName = distName;
	}
	public String getDistAddress() {
		return distAddress;
	}
	public void setDistAddress(String distAddress) {
		this.distAddress = distAddress;
	}
	public String getDistContact() {
		return distContact;
	}
	public void setDistContact(String distContact) {
		this.distContact = distContact;
	}


}
