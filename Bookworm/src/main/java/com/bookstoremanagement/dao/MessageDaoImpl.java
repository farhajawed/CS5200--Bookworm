package com.bookstoremanagement.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bookstoremanagement.model.Book;
import com.bookstoremanagement.model.Messages;

@Repository("Message")
@Transactional
public class MessageDaoImpl implements MessageDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	public void saveMessage(Messages message) {

		Session currentSession = sessionFactory.getCurrentSession();
		currentSession.saveOrUpdate(message);
	}
	
	public List<Messages> list(int id) {
		
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();

		// create a query
		Query<Messages> theQuery = currentSession.createQuery("from Messages WHERE conversation.id = :id "
				+ "order by date desc", Messages.class);
		
		theQuery.setParameter("id",id);

		// execute query and get result list
		List<Messages> messages = theQuery.getResultList();

		// return the results
		return messages;
	}

}
