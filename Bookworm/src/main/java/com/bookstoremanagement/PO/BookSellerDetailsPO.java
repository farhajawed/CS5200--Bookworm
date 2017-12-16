package com.bookstoremanagement.PO;

import com.bookstoremanagement.model.Book;
import com.bookstoremanagement.model.SellerDetails;

public class BookSellerDetailsPO {
	
	private Book book;
	private float sellerPrice;
	private int copiesAvailable;
	private int copiesSold;
	
	public BookSellerDetailsPO(SellerDetails sellerDetail) {
		this.book = sellerDetail.getBook();
		this.sellerPrice = sellerDetail.getPrice();
		this.copiesAvailable = sellerDetail.getQuantity();
	}
	
	
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	public float getSellerPrice() {
		return sellerPrice;
	}
	public void setSellerPrice(float sellerPrice) {
		this.sellerPrice = sellerPrice;
	}
	public int getCopiesAvailable() {
		return copiesAvailable;
	}
	public void setCopiesAvailable(int copiesAvailable) {
		this.copiesAvailable = copiesAvailable;
	}
	public int getCopiesSold() {
		return copiesSold;
	}
	public void setCopiesSold(int copiesSold) {
		this.copiesSold = copiesSold;
	}
}
