package com.bookstoremanagement.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name="seller_book_detail")
public class SellerDetails {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@ManyToOne(cascade={CascadeType.DETACH, CascadeType.MERGE,
		     CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinColumn(name="user_id")
	private UserLogin userLogin;
	
	@ManyToOne(cascade={CascadeType.DETACH, CascadeType.MERGE,
		     CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinColumn(name="book_id")
	private Book book;
	
	@Column(name="price")
	private float price;
	
	@Column(name="quantity")
	private int quantity;
	
	@Column(name="book_condition")
	private String bookCondition;
	
	@Column(name="date_created")
	@UpdateTimestamp
	private LocalDateTime createDateTime;
	
//	@UpdateTimestamp
//	@Temporal(TemporalType.TIMESTAMP)
//	@Column(name = "modify_date")
//	private LocalDateTime modifyDate;
	
	@OneToMany
	(mappedBy="sellerdetails")
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<WishList> wishList;
	
	@OneToMany(mappedBy="sellerdetails")
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<OrderBookDetails> orderBookDetails;
	
	public void addToWishList(WishList wl) {
		if (wl == null) {
			return;
		}
		wl.setSellerBookDetails(this);
		if (wishList == null) {
			wishList = new ArrayList<WishList>();
			wishList.add(wl);
		} else if (!wishList.contains(wl)) {
			wishList.add(wl);
		}
	}

	public void addToOrderBookList(OrderBookDetails orderBookDetail) {
		if (orderBookDetail == null) {
			return;
		}
		orderBookDetail.setSellerdetails(this);
		if (orderBookDetails == null) {
			orderBookDetails = new ArrayList<OrderBookDetails>();
			orderBookDetails.add(orderBookDetail);
		} else if (!orderBookDetails.contains(orderBookDetail)) {
			orderBookDetails.add(orderBookDetail);
		}
	}
	public SellerDetails(Book book, float price, int quantity) {
		super();
		this.book = book;
		this.price = price;
		this.quantity = quantity;
	}

	public SellerDetails(float price, int quantity, String bookCondition) {
		super();
		this.price = price;
		this.quantity = quantity;
		this.bookCondition = bookCondition;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public UserLogin getUserLogin() {
		return userLogin;
	}

	public void setUserLogin(UserLogin userLogin) {
		this.userLogin = userLogin;
	}

	public List<WishList> getWishList() {
		return wishList;
	}

	public void setWishList(List<WishList> wishList) {
		this.wishList = wishList;
	}

	public List<OrderBookDetails> getOrderBookDetails() {
		return orderBookDetails;
	}

	public void setOrderBookDetails(List<OrderBookDetails> orderBookDetails) {
		this.orderBookDetails = orderBookDetails;
	}

	public String getBookCondition() {
		return bookCondition;
	}

	public void setBookCondition(String bookCondition) {
		this.bookCondition = bookCondition;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}


	public LocalDateTime getCreateDateTime() {
		return createDateTime;
	}

	public void setCreateDateTime(LocalDateTime createDateTime) {
		this.createDateTime = createDateTime;
	}

	public SellerDetails() {
		
	}

	
	
}