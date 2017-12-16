package com.bookstoremanagement.model;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name="rating")
public class Rating {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@ManyToOne(cascade={CascadeType.DETACH, CascadeType.MERGE,
		     CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinColumn(name="user_id")
	private UserLogin userLogin;
	
	@ManyToOne(cascade={CascadeType.DETACH,
		     CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinColumn(name="book_id")
	private Book book;
	
	@Column(name="score")
	private float score;
	
	@Column(name="review")
	private String review;
	
	@Column(name="date_rated")
	@UpdateTimestamp
	private LocalDateTime dt;

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

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public float getScore() {
		return score;
	}

	public void setScore(float score) {
		this.score = score;
	}

	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
	}


	public Rating() {
		super();
	}

	public LocalDateTime getDt() {
		return dt;
	}

	public void setDt(LocalDateTime dt) {
		this.dt = dt;
	}

	@Override
	public String toString() {
		return "Rating [id=" + id + ", userLogin=" + userLogin + ", book=" + book + ", score=" + score + ", review="
				+ review + ", dt=" + dt + "]";
	}


}
