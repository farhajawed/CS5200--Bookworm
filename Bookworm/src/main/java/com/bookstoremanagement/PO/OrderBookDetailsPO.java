package com.bookstoremanagement.PO;

import java.util.ArrayList;
import java.util.List;

import com.bookstoremanagement.model.Book;
import com.bookstoremanagement.model.OrderBookDetails;
import com.bookstoremanagement.model.OrderDetails;
import com.bookstoremanagement.model.SellerDetails;

public class OrderBookDetailsPO {
	
	public OrderBookDetailsPO() {
		super();
	}

	public OrderBookDetailsPO(OrderBookDetails cart) {
		this.order = new OrderDetailsPO(cart.getOrderdetails());
		this.sellerdetails = cart.getSellerdetails();
	}

	public OrderBookDetailsPO(List<OrderDetailsPO> orders, Book book, OrderDetailsPO order, OrderDetails orderdetails,
			SellerDetails sellerdetails) {
		super();
		this.orders = orders;
		this.book = book;
		this.order = order;
		this.sellerdetails = sellerdetails;
	}

	private List<OrderDetailsPO> orders = new ArrayList<OrderDetailsPO>();
	
	private Book book;
	
	private OrderDetailsPO order;

	private SellerDetails sellerdetails;


	public List<OrderDetailsPO> getOrders() {
		return orders;
	}
	public void setOrders(List<OrderDetailsPO> orders) {
		this.orders = orders;
	}
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	public OrderDetailsPO getOrder() {
		return order;
	}
	public void setOrder(OrderDetailsPO order) {
		this.order = order;
	}
	public SellerDetails getSellerdetails() {
		return sellerdetails;
	}
	public void setSellerdetails(SellerDetails sellerdetails) {
		this.sellerdetails = sellerdetails;
	}
	@Override
	public String toString() {
		return "CartDetails [orders=" + orders + ", book=" + book + ", order=" + order + "]";
	}
}
