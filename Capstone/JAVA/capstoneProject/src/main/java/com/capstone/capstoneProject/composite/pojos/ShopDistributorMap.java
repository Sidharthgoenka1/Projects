package com.capstone.capstoneProject.composite.pojos;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "SHOP_DISTRIBUTOR_MAP")
public class ShopDistributorMap {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Integer distId;
	private Integer shopId;
	private Date registeringDate;
	public Integer getDistId() {
		return distId;
	}
	public void setDistId(Integer distId) {
		this.distId = distId;
	}
	public Integer getShopId() {
		return shopId;
	}
	public void setShopId(Integer shopId) {
		this.shopId = shopId;
	}
	public Date getRegisteringDate() {
		return registeringDate;
	}
	public void setRegisteringDate(Date registeringDate) {
		this.registeringDate = registeringDate;
	}
	@Override
	public String toString() {
		return "ShopDistributorMapping [DistId=" + distId + ", shopId=" + shopId + ", registeringDate="
				+ registeringDate + "]";
	}
	
	
}
