package com.bookstoremanagement.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.bookstoremanagement.model.Rating;

@Repository("Rating")
@Transactional


public class RatingDaoImpl implements RatingDao{
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public void saveRating(Rating rating) {

		Session currentSession = sessionFactory.getCurrentSession();
		currentSession.saveOrUpdate(rating);
		
	}
	
	public Rating searchByIsbnUserName(String isbn, String username) {
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		Query<Rating> theQuery = currentSession.createQuery("from Rating WHERE book.isbn = :isbn "
				+ "AND userLogin.username =:username", Rating.class);
		
		theQuery.setParameter("isbn",isbn);
		theQuery.setParameter("username",username);

		
		// now retrieve/read from db using primary key
		Rating rating = (Rating) theQuery.setMaxResults(1).uniqueResult();
		
		return rating;
	}
	
	public List<Rating> getRatings(String isbn) {
		
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// create a query
		Query<Rating> theQuery = currentSession.createQuery("from Rating WHERE book.isbn = :isbn order by dt desc", 
															Rating.class);
		theQuery.setParameter("isbn",isbn);
		
		// execute query and get result list
		List<Rating> ratings = theQuery.getResultList();
		
		
		// return the results
		return ratings;
	}
	
	public Object getAverageRating(String isbn){
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();

		// create a query
		
		String hql="SELECT avg(score) FROM Rating  WHERE book.isbn = :isbn";
		
		Query theQuery=currentSession.createQuery(hql);
		
		theQuery.setParameter("isbn",isbn);
		
		Object avg= theQuery.list().get(0);
		
		return avg;
	}

}