package com.bookstoremanagement.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bookstoremanagement.model.Book;
import com.bookstoremanagement.model.ConversationMembers;

@Repository("ConversationMember")
@Transactional
public class ConversationMembersDaoImpl implements ConversationMembersDao {


	@Autowired
	private SessionFactory sessionFactory;
	
	public void saveConversationMember(ConversationMembers conversationMember) {

		Session currentSession = sessionFactory.getCurrentSession();
		currentSession.saveOrUpdate(conversationMember);
	}
	
	public ConversationMembers searchByConversationIdAndUsername(String username,int id) {
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		Query<ConversationMembers> theQuery = 
				currentSession.createQuery
				("from ConversationMembers WHERE conversation.id = :id"
				+ " AND userLogin.username!= :username", ConversationMembers.class);
		
		theQuery.setParameter("id",id);
		theQuery.setParameter("username",username);

		
		// now retrieve/read from db using primary key
		ConversationMembers conversationMembers = (ConversationMembers) theQuery.getSingleResult();
		
		return conversationMembers;
	}
	
	public ConversationMembers searchByConversationIdAndCurrentUsername(String username,int id) {
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		Query<ConversationMembers> theQuery = 
				currentSession.createQuery
				("from ConversationMembers WHERE conversation.id = :id"
				+ " AND userLogin.username= :username", ConversationMembers.class);
		
		theQuery.setParameter("id",id);
		theQuery.setParameter("username",username);

		
		// now retrieve/read from db using primary key
		ConversationMembers conversationMembers = (ConversationMembers) theQuery.getSingleResult();
		
		return conversationMembers;
	}
	
	
}
