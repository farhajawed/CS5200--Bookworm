package com.bookstoremanagement.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;


@Entity
@Table(name="payment")
public class Payment {

	@GenericGenerator(name= "generator", strategy ="foreign")
	@Id
	@Column(name = "orderdetails", unique = true, nullable = false)
	private int id;
	
	@OneToOne
	@JoinColumn(name="orderdetails")
	private OrderDetails orderdetails;
	
	@Column(name="payStatus")
	private String payStatus;
	
	@Column(name="modeOfPay")
	private String modeOfPay;

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

	public String getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(String payStatus) {
		this.payStatus = payStatus;
	}

	public String getModeOfPay() {
		return modeOfPay;
	}

	public void setModeOfPay(String modeOfPay) {
		this.modeOfPay = modeOfPay;
	}
	
}
