package com.bookstoremanagement.dao;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bookstoremanagement.model.Book;
import com.bookstoremanagement.model.Category;


@Repository("bookDAO")
@Transactional
public class BookDaoImpl implements BookDao{
	
		@Autowired
		private SessionFactory sessionFactory;

		public List<Book> list(String term) {
			
			// get the current hibernate session
			Session currentSession = sessionFactory.getCurrentSession();

			// create a query
			Query<Book> theQuery = currentSession.createQuery("from Book WHERE title like :title OR isbn = :isbn", Book.class);
			
			theQuery.setParameter("title","%"+term+"%");
			theQuery.setParameter("isbn",term);

			// execute query and get result list
			List<Book> booklist = theQuery.getResultList();

			// return the results
			return booklist;
		}
		
		public Book searchByIsbn(String isbn) {
			// get the current hibernate session
			Session currentSession = sessionFactory.getCurrentSession();
			
			Query<Book> theQuery = currentSession.createQuery("from Book WHERE isbn = :isbn", Book.class);
			
			theQuery.setParameter("isbn",isbn);

			
			// now retrieve/read from db using primary key
			Book book = (Book) theQuery.getSingleResult();
			
			return book;
		}
		
		public Book getBook(int theId) {
			Session currentSession = sessionFactory.getCurrentSession();
			Book book = currentSession.get(Book.class,theId);
			return book;
			
		}
		
		public int getPk(String isbn) {
			Session currentSession = sessionFactory.getCurrentSession();
			int id=0;
			Query<Book> theQuery = currentSession.createQuery("from Book WHERE isbn = :isbn", Book.class);
			theQuery.setParameter("isbn",isbn);
			Book book = (Book) theQuery.setMaxResults(1).uniqueResult();
			if(book==null) {
				id=0;}
			else {
				id = book.getId();}
			return id;
		}
		
		public void saveBook(Book book) {
			
			Session currentSession = sessionFactory.getCurrentSession();
			currentSession.saveOrUpdate(book);
			
		}
			
}