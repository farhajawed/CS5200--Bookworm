package com.bookstoremanagement.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;



@Entity
@Table(name="messages")
public class Messages {
	
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
	@JoinColumn(name="conversation_id")
	private Conversation conversation;
	
	@Column(name="date")
	private long date;
	
	@Column(name="text")
	private String text;

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

	public Conversation getConversation() {
		return conversation;
	}

	public void setConversation(Conversation conversation) {
		this.conversation = conversation;
	}

	public long getDate() {
		return date;
	}

	public void setDate(long date) {
		this.date = date;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Messages() {
		super();
	}

	
	
}
