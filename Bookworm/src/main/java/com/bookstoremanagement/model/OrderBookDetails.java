package com.bookstoremanagement.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="orderdetails")
public class OrderBookDetails {
	@Id
	@Column(name="id")
	private int id;
	
	@ManyToOne
	@JoinColumn(name="cart")
	private OrderDetails orderdetails;
	
	@ManyToOne
	@JoinColumn(name="sellerbookdetails")
	private SellerDetails sellerdetails;
	
	@Column(name="bookcount")
	private int bookCount;
	
	@Column(name="updated")
	private Date updatedOn;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public OrderDetails getOrderdetails() {
		return orderdetails;
	}

	public void setOrderdetails(OrderDetails orderdetails) {
		this.orderdetails = orderdetails;
	}

	public SellerDetails getSellerdetails() {
		return sellerdetails;
	}

	public void setSellerdetails(SellerDetails sellerdetails) {
		this.sellerdetails = sellerdetails;
	}

	public int getBookCount() {
		return bookCount;
	}

	public void setBookCount(int bookCount) {
		this.bookCount = bookCount;
	}

	public Date getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}

	@Override
	public String toString() {
		return "Cart [id=" + id +" sellerdetails=" + sellerdetails+ ", bookCount=" + bookCount + "]";
	}
	
	
}
