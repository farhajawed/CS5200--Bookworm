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
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="category")
public class Category {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="name")
	private String name;
	
	@OneToMany(mappedBy="category",fetch=FetchType.EAGER,
			cascade={CascadeType.DETACH, CascadeType.MERGE,
				     CascadeType.PERSIST, CascadeType.REFRESH})
	private List<Book>books;

	public Category() {
		super();
	}

	public Category(String name) {
		super();
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}
	
	// add convenience methods for bi-directional relationship
	public void add(Book tempBook) {
	 if(books == null) {
		books = new ArrayList();
	  }
	 books.add(tempBook);
	 tempBook.setCategory(this);
	}
	
	
}
