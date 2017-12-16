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
@Table(name="conversation")
public class Conversation {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="subject")
	private String subject;
	
	
	@OneToMany(mappedBy="conversation",fetch=FetchType.EAGER,
			cascade = CascadeType.ALL)
	private List<ConversationMembers> conversationMembers;
	
	@OneToMany(mappedBy="conversation",
			cascade = CascadeType.ALL)
	private List<Messages> message;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public List<ConversationMembers> getConversationMembers() {
		return conversationMembers;
	}

	public void setConversationMembers(List<ConversationMembers> conversationMembers) {
		this.conversationMembers = conversationMembers;
	}

	public List<Messages> getMessage() {
		return message;
	}

	public void setMessage(List<Messages> message) {
		this.message = message;
	}

	public Conversation() {
		super();
	}

	
	// add convenience methods for bi-directional relationship
	public void add(ConversationMembers tempConversationMembers) {
	   if(conversationMembers == null) {
		   conversationMembers = new ArrayList();
	   }
	   conversationMembers.add(tempConversationMembers);
	   tempConversationMembers.setConversation(this);
	}
		
}
