package com.bookstoremanagement.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.bookstoremanagement.PO.RegistrationPO;

@Entity
@Table(name="useraddress")
public class UserAddress {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@ManyToOne
    @JoinColumn(name="userdetails", nullable=false)
    private UserInfo userdetails;
	
	@NotEmpty
	@NotBlank(groups = {RegistrationPO.class})
	@Size(min = 4, max = 20)
	@Column(name="street1")
	private String street1;
	
	@Column(name="street2")
	private String street2;
	
	@NotEmpty
	@NotBlank(groups = {RegistrationPO.class})
	@Size(min = 4, max = 20)
	@Column(name="city")
	private String city;
	
	@NotEmpty
	@NotBlank(groups = {RegistrationPO.class})
	@Size(min = 2, max = 20)
	@Column(name="state")
	private String state;
	
	@NotEmpty
	@NotBlank(groups = {RegistrationPO.class})
	@Size(min = 5, max = 5)
	@Column(name="pincode")
	private String pincode;
	
	@Column(name="isPrimary")
	private String isPrimary = "N";
	
	public UserInfo getUserdetails() {
		return userdetails;
	}

	public void setUserdetails(UserInfo userdetails) {
		this.userdetails = userdetails;
	}

	public String getStreet1() {
		return street1;
	}

	public void setStreet1(String street1) {
		this.street1 = street1;
	}

	public String getStreet2() {
		return street2;
	}

	public void setStreet2(String street2) {
		this.street2 = street2;
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

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	public String getIsPrimary() {
		return isPrimary;
	}

	public void setIsPrimary(String isPrimary) {
		this.isPrimary = isPrimary;
	}

	@Override
	public String toString() {
		return "UserAddress [Id=" + id + ", street1=" + street1 + ", street2=" + street2 + ", city=" + city + ", state="
				+ state + ", pincode=" + pincode + "]";
	}


}
