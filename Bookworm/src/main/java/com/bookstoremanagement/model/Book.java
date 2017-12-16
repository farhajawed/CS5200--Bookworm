package com.bookstoremanagement.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="book")
public class Book {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="isbn")
	private String isbn;
	
	@Column(name="title")
	private String title;
	
	@ManyToOne(cascade={CascadeType.DETACH, CascadeType.MERGE,
		     CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinColumn(name="author_id")
	private Author author;
	
	@ManyToOne(cascade={CascadeType.DETACH, CascadeType.MERGE,
		     CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinColumn(name="category_id")
	private Category category;

	@Column(name="image_link")
	private String imageLink;
	
	@OneToMany(mappedBy="book",fetch=FetchType.EAGER,
			cascade={CascadeType.DETACH, CascadeType.MERGE,
				     CascadeType.PERSIST, CascadeType.REFRESH})
	private List<SellerDetails> sellerDetails;
	
	@OneToMany(mappedBy="book",
			cascade={CascadeType.DETACH,CascadeType.MERGE,
				     CascadeType.PERSIST, CascadeType.REFRESH})
	private List<Rating> rating;
	
	public Book() {
		super();
	}

	public Book(String isbn, String title, Author author, Category category, String imageLink) {
		this.isbn = isbn;
		this.title = title;
		this.author = author;
		this.category = category;
		this.imageLink = imageLink;
	}

	public Book(String isbn, String title, Author author, Category category, String imageLink,List<SellerDetails> sellers) {
		this.isbn = isbn;
		this.title = title;
		this.author = author;
		this.category = category;
		this.imageLink = imageLink;
		this.sellerDetails = sellers;
	}

	public Book(String isbn, String title, String imageLink) {
		super();
		this.isbn = isbn;
		this.title = title;
		this.imageLink = imageLink;
	}

	public Book(String isbn) {
		this.isbn = isbn;
	}

	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getImageLink() {
		return imageLink;
	}

	public void setImageLink(String imageLink) {
		this.imageLink = imageLink;
	}

	public List<SellerDetails> getSellerDetails() {
		return sellerDetails;
	}

	public void setSellerDetails(List<SellerDetails> sellerDetails) {
		this.sellerDetails = sellerDetails;
	}
	
	public List<Rating> getRating() {
		return rating;
	}

	public void setRating(List<Rating> rating) {
		this.rating = rating;
	}

	// add convenience methods for bi-directional relationship
	public void add(SellerDetails tempSellerDetails) {
	   if(sellerDetails == null) {
		   sellerDetails = new ArrayList();
	   }
	   sellerDetails.add(tempSellerDetails);
	   tempSellerDetails.setBook(this);
	}
	
	// add convenience methods for bi-directional relationship
		public void add(Rating tempRating) {
		   if(rating == null) {
			   rating = new ArrayList();
		   }
		   rating.add(tempRating);
		   tempRating.setBook(this);
		}

	@Override
	public String toString() {
		return "Book [id=" + id + ", isbn=" + isbn + ", title=" + title + ", author=" + author + ", category="
				+ category + ", imageLink=" + imageLink + "]";
	}
	
	
}
