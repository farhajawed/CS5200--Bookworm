package com.bookstoremanagement.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bookstoremanagement.model.Conversation;
import com.bookstoremanagement.model.SellerDetails;


@Repository("Conversation")
@Transactional
public class ConversationDaoImpl implements ConversationDao{

	@Autowired
	private SessionFactory sessionFactory;
	
	public void saveConversation(Conversation conversation) {

		Session currentSession = sessionFactory.getCurrentSession();
		currentSession.saveOrUpdate(conversation);
	}
	
	
	public List<Object> list(String username){
		Session currentSession = sessionFactory.getCurrentSession();	
		String hql="Select c.id,c.subject, max(m.date)  as last_reply,"
				+ "max(m.date) > cm.lastView as unread from "
				+ "Conversation as c LEFT JOIN c.conversationMembers cm LEFT JOIN c.message m "
				+ "WHERE cm.userLogin.username = :username AND cm.deleted=0 GROUP BY c.id, c.subject, cm.lastView ORDER BY last_reply DESC";
		Query theQuery=currentSession.createQuery(hql);
		theQuery.setParameter("username",username);
		List<Object> list = theQuery.list();
		return list;
	}
	
	public void deleteConversation(int id) {
		Session currentSession = sessionFactory.getCurrentSession();
				
		// delete object with primary key
		Query theQuery = currentSession.createQuery("delete from Conversation where id = :id");
		theQuery.setParameter("id", id);
		theQuery.executeUpdate();
	}
	
	public Conversation getConversation(int id) {
		Session currentSession = sessionFactory.getCurrentSession();
		Conversation conversation = currentSession.get(Conversation.class,id);
		return conversation;
		
	}
}
