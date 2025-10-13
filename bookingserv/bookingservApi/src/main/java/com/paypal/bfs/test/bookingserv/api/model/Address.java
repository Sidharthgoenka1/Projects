package com.paypal.bfs.test.bookingserv.api.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;

import org.springframework.stereotype.Component;

@Entity
public class Address 
{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@NotBlank(message = "line1 is mandatory")
	private String line1;
	private String line2;
	@NotBlank(message = "city is mandatory")
	private String city;
	@NotBlank(message = "state is mandatory")
	private String state;
	 @NotBlank(message = "zipcode is mandatory")
	private String zipcode;
	
	@OneToOne(mappedBy = "address")
	private Booking booking;
	
	public String getLine1() {
		return line1;
	}
	public void setLine1(String line1) {
		this.line1 = line1;
	}
	public String getLine2() {
		return line2;
	}
	public void setLine2(String line2) {
		this.line2 = line2;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	@Override
	public String toString() {
		return "Address [line1=" + line1 + ", line2=" + line2 + ", city=" + city + ", state=" + state + ", zipcode="
				+ zipcode + "]";
	}
	
	
	

}
