package com.bookstoremanagement.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="WISHLIST")
public class WishList {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@ManyToOne
	@JoinColumn(name="USERDETAILS")
	private UserInfo userdetails;
	
	@ManyToOne
	@JoinColumn(name="sellerbookdetails")
	private SellerDetails sellerdetails;
	
	@Column(name="created")
	private Date createdOn;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public UserInfo getUserDetails() {
		return userdetails;
	}

	public void setUserDetails(UserInfo userDetails) {
		this.userdetails = userDetails;
	}

	public SellerDetails getSellerBookDetails() {
		return sellerdetails;
	}

	public void setSellerBookDetails(SellerDetails sellerBookDetails) {
		this.sellerdetails = sellerBookDetails;
	}
	
	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	@Override
	public String toString() {
		return "WishList [id=" + id + ", userdetails=" + userdetails + ", sellerdetails=" + sellerdetails + "]";
	}


}
