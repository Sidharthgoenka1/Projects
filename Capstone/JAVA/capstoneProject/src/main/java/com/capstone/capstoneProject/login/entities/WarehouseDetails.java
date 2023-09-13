package com.capstone.capstoneProject.login.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "WAREHOUSE_DETAILS")
public class WarehouseDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String warehouseName;
	private String warehouseAddress;
	private String warehouseContact;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getWarehouseName() {
		return warehouseName;
	}
	public void setWarehouseName(String warehouseName) {
		this.warehouseName = warehouseName;
	}
	public String getWarehouseAddress() {
		return warehouseAddress;
	}
	public void setWarehouseAddress(String warehouseAddress) {
		this.warehouseAddress = warehouseAddress;
	}
	public String getWarehouseContact() {
		return warehouseContact;
	}
	public void setWarehouseContact(String warehouseContact) {
		this.warehouseContact = warehouseContact;
	}
	@Override
	public String toString() {
		return "WarehouseDetails [id=" + id + ", warehouseName=" + warehouseName + ", warehouseAddress="
				+ warehouseAddress + ", warehouseContact=" + warehouseContact + "]";
	}

	
	

}
