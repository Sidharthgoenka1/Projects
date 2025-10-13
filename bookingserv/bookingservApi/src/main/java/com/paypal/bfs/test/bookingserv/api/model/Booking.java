
package com.paypal.bfs.test.bookingserv.api.model;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;



@Entity
public class Booking {

    /**
     * Booking id
     * 
     */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    /**
     * First name
     * (Required)
     * 
     */

    private String firstName;
    /**
     * Last name
     * (Required)
     * 
     */
    
    private String lastName;
   
    
    private String dob;
    
    @Temporal(TemporalType.DATE)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", locale = "hi_IN", timezone = "IST")
    private Date checkinDateTime;
    
    @Temporal(TemporalType.DATE)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", locale = "hi_IN", timezone = "IST")
    private Date checkoutDateTime;
    
    private Float totalPrice;
    
    private Float deposit;
    
    
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;
    
    
   
    public Integer getId() {
        return id;
    }

   
    
    public void setId(Integer id) {
        this.id = id;
    }

   
    public String getFirstName() {
        return firstName;
    }

  
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

   
    public String getLastName() {
        return lastName;
    }

  
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    
  

    public String getDob() {
		return dob;
	}



	public void setDob(String dob) {
		this.dob = dob;
	}



	public Date getCheckinDateTime() {
		return checkinDateTime;
	}



	public void setCheckinDateTime(Date checkinDateTime) {
		this.checkinDateTime = checkinDateTime;
	}



	public Date getCheckoutDateTime() {
		return checkoutDateTime;
	}



	public void setCheckoutDateTime(Date checkoutDateTime) {
		this.checkoutDateTime = checkoutDateTime;
	}



	public Float getTotalPrice() {
		return totalPrice;
	}



	public void setTotalPrice(Float totalPrice) {
		this.totalPrice = totalPrice;
	}



	public Float getDeposit() {
		return deposit;
	}



	public void setDeposit(Float deposit) {
		this.deposit = deposit;
	}



	public Address getAddress() {
		return address;
	}



	public void setAddress(Address address) {
		this.address = address;
	}



	@Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Booking.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("id");
        sb.append('=');
        sb.append(((this.id == null)?"<null>":this.id));
        sb.append(',');
        sb.append("firstName");
        sb.append('=');
        sb.append(((this.firstName == null)?"<null>":this.firstName));
        sb.append(',');
        sb.append("lastName");
        sb.append('=');
        sb.append(((this.lastName == null)?"<null>":this.lastName));
        
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

}
