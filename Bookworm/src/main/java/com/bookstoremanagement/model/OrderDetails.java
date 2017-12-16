package com.bookstoremanagement.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.bookstoremanagement.PO.OrderDetailsPO;

@Entity
@Table(name="cart")
public class OrderDetails {

	public OrderDetails() {
		super();
	}

	public OrderDetails(int id, float amount, int quantity, UserInfo userdetails, List<OrderBookDetails> orderbookdetails) {
		super();
		this.id = id;
		this.amount = amount;
		this.quantity = quantity;
		this.userdetails = userdetails;
		this.orderbookdetails = orderbookdetails;
	}

	public OrderDetails(OrderDetailsPO orderPO) {
		this.id = orderPO.getId();
		this.amount = orderPO.getAmount();
		this.quantity = orderPO.getQuantity();
		this.userdetails = orderPO.getUserdetails();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;

	@Column(name="amount")
	private float amount;

	@Column(name="quantity")
	private int quantity;

	@Column(name="payStatus")
	private String payStatus;

	@Column(name="modeOfPay")
	private String modeOfPay;
	
	@Column(name="created")
	private Date createdOn;

	@Column(name="updated")
	private Date updatedOn;

	@ManyToOne
	@JoinColumn(name="userdetails", nullable=false)
	private UserInfo userdetails; 

	@OneToMany(mappedBy="orderdetails")
	@Cascade(CascadeType.ALL)
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<OrderBookDetails> orderbookdetails;

	public void addToCart(OrderBookDetails orderbookdetail) {
		if (orderbookdetail == null) {
			return;
		}
		orderbookdetail.setOrderdetails(this);
		if(this.getId() != 0) {
			orderbookdetail.setUpdatedOn(new Date(System.currentTimeMillis()));
		}
		if (orderbookdetails == null) {
			orderbookdetails = new ArrayList<OrderBookDetails>();
			orderbookdetails.add(orderbookdetail);
		} else if (!orderbookdetails.contains(orderbookdetail)) {
			orderbookdetails.add(orderbookdetail);
		}
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
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

	public Date getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}

	public UserInfo getUserdetails() {
		return userdetails;
	}

	public void setUserdetails(UserInfo userdetails) {
		this.userdetails = userdetails;
	}

	public List<OrderBookDetails> getOrderbookdetails() {
		return orderbookdetails;
	}

	public void setOrderbookdetails(List<OrderBookDetails> orderbookdetails) {
		this.orderbookdetails = orderbookdetails;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}
	
	@Override
	public String toString() {
		return "OrderDetails [id=" + id + ", amount=" + amount + ", quantity=" + quantity + ", payStatus=" + payStatus
				+ ", modeOfPay=" + modeOfPay + ", userdetails=" + userdetails + ", cart=" + orderbookdetails
				+ "]";
	}


}
