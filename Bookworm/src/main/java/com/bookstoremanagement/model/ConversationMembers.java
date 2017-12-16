package com.bookstoremanagement.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="conversation_members")
public class ConversationMembers {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@ManyToOne(cascade={CascadeType.DETACH, CascadeType.MERGE,
		     CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinColumn(name="conversation_id")
	private Conversation conversation;
	
	@ManyToOne(cascade={CascadeType.DETACH, CascadeType.MERGE,
		     CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinColumn(name="user_id")
	private UserLogin userLogin;
	
	@Column(name="last_view")
	private long lastView;
	
	
	@Column(name="deleted")
	private boolean deleted;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Conversation getConversation() {
		return conversation;
	}

	public long getLastView() {
		return lastView;
	}

	public void setLastView(long lastView) {
		this.lastView = lastView;
	}

	public void setConversation(Conversation conversation) {
		this.conversation = conversation;
	}

	public UserLogin getUserLogin() {
		return userLogin;
	}

	public void setUserLogin(UserLogin userLogin) {
		this.userLogin = userLogin;
	}

	
	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public ConversationMembers() {
		super();
	}

	
	
	
	
}
