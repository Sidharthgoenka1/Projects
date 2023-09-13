package com.capstone.capstoneProject.dashboard.model;

public class InventoryMaster {
	private Integer id;
	private String name;
	private String shortDescription;
	private String description;
	private String salts;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getShortDescription() {
		return shortDescription;
	}
	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getSalts() {
		return salts;
	}
	public void setSalts(String salts) {
		this.salts = salts;
	}

	@Override
	public String toString() {
		return "InventoryMaster{" +
				"id=" + id +
				", name='" + name + '\'' +
				", shortDescription='" + shortDescription + '\'' +
				", description='" + description + '\'' +
				", salts='" + salts + '\'' +
				'}';
	}
}
