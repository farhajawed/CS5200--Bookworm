package com.bookstoremanagement.PO;

import java.sql.Date;
import java.util.List;

import javax.persistence.Column;

import com.bookstoremanagement.model.Book;
import com.bookstoremanagement.model.OrderDetails;
import com.bookstoremanagement.model.UserInfo;

public class OrderDetailsPO {

	public OrderDetailsPO() {
		super();
	}

	public OrderDetailsPO(int id, float amount, int quantity,float unitPrice,  UserInfo userdetails, List<Book> books) {
		super();
		this.id = id;
		this.amount = amount;
		this.unitPrice = unitPrice;
		this.quantity = quantity;
		this.userdetails = userdetails;
		this.books = books;
	}
	
	public OrderDetailsPO(OrderDetails orderDetails) {
		super();
		this.id = orderDetails.getId();
		this.amount = orderDetails.getAmount();
		this.quantity = orderDetails.getQuantity();
		this.userdetails = orderDetails.getUserdetails();
		this.payStatus = orderDetails.getPayStatus();
		this.modeOfPay = orderDetails.getModeOfPay();
		this.createdOn = orderDetails.getCreatedOn();
		this.updatedOn = orderDetails.getUpdatedOn();
	}
	
	private int id;
	private float amount;
	private float unitPrice;
	private int quantity;
	private UserInfo userdetails; 
	private String payStatus;
	private String modeOfPay;
	private List<Book> books;
	private Book book;
	private int sellerId;
	private Date updatedOn;
	private Date createdOn;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float unitPrice2) {
		this.amount = unitPrice2;
	}

	public float getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(float unitPrice2) {
		this.unitPrice = unitPrice2;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Date getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public UserInfo getUserdetails() {
		return userdetails;
	}

	public void setUserdetails(UserInfo userdetails) {
		this.userdetails = userdetails;
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

	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public int getSellerId() {
		return sellerId;
	}

	public void setSellerId(int sellerId) {
		this.sellerId = sellerId;
	}
	@Override
	public String toString() {
		return "OrderDetailsPO [id=" + id + ", amount=" + amount+ ", quantity=" + quantity
				+", userdetails=" + userdetails + ", payStatus=" + payStatus
				+ ", modeOfPay=" + modeOfPay + ", books=" + books + ", book=" + book + ", sellerId=" + sellerId + "]";
	}

}
