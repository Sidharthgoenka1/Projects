package com.capstone.capstoneProject.login;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {

	private Integer userId;
	private String username;
	private String password;
	private String name;
	private String email;
	private String role;
	private String address;
	private String contactNo;
	private Integer workplaceId;
	private String userType;
	private String workplaceName;
	private String workplaceAddress;
	private String workplaceContactNo;
	
	
	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	@Override
	public String toString() {
		return "RegisterRequest [userId=" + userId + ", username=" + username + ", password=" + password + ", name="
				+ name + ", email=" + email + ", role=" + role + ", address=" + address + ", contactNo=" + contactNo
				+ ", workplaceId=" + workplaceId + ", userType=" + userType + ", workplaceName=" + workplaceName
				+ ", workplaceAddress=" + workplaceAddress + ", workplaceContactNo=" + workplaceContactNo + "]";
	}
	public String getWorkplaceName() {
		return workplaceName;
	}
	public void setWorkplaceName(String workplaceName) {
		this.workplaceName = workplaceName;
	}
	public String getWorkplaceAddress() {
		return workplaceAddress;
	}
	public void setWorkplaceAddress(String workplaceAddress) {
		this.workplaceAddress = workplaceAddress;
	}
	public String getWorkplaceContactNo() {
		return workplaceContactNo;
	}
	public void setWorkplaceContactNo(String workplaceContactNo) {
		this.workplaceContactNo = workplaceContactNo;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getContactNo() {
		return contactNo;
	}
	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}
	public Integer getWorkplaceId() {
		return workplaceId;
	}
	public void setWorkplaceId(Integer workplaceId) {
		this.workplaceId = workplaceId;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
