package com.bookstoremanagement.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.bookstoremanagement.model.Author;


@Repository("Author")
@Transactional
public class AuthorDaoImpl implements AuthorDao{
	
	
		@Autowired
		private SessionFactory sessionFactory;
		

		public List<Author> getAuthors() {
			
			// get the current hibernate session
			Session currentSession = sessionFactory.getCurrentSession();
			
			// create a query
			Query<Author> theQuery = currentSession.createQuery("from Author order by name", 
																Author.class);
			
			// execute query and get result list
			List<Author> authors = theQuery.getResultList();
			
			
			// return the results
			return authors;
		}


		public void saveAuthor(Author author) {
	
			Session currentSession = sessionFactory.getCurrentSession();
			currentSession.saveOrUpdate(author);
		}
		
		public int getPk(String name) {
		
			int id=0;
			Session currentSession = sessionFactory.getCurrentSession();
			
			Query<Author> theQuery = currentSession.createQuery("from Author WHERE name = :name", Author.class);
			
			theQuery.setParameter("name",name);

			Author author = (Author) theQuery.setMaxResults(1).uniqueResult();
			if(author==null) {
				id=0;
			}
			else {
				id = author.getId();
			}
			return id;
		}
		
		
		public Author getAuthor(int theId) {
			Session currentSession = sessionFactory.getCurrentSession();
			Author theAuthor = currentSession.get(Author.class,theId);
			
			return theAuthor;
			
		}
}
