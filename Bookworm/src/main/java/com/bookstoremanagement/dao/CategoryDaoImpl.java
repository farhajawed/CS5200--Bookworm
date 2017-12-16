package com.bookstoremanagement.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bookstoremanagement.model.Author;
import com.bookstoremanagement.model.Category;


@Repository("Category")
@Transactional
public class CategoryDaoImpl implements CategoryDao{
	
	
		@Autowired
		private SessionFactory sessionFactory;
		
		public List<Category> getCategory() {
			
			// get the current hibernate session
			Session currentSession = sessionFactory.getCurrentSession();
			
			// create a query
			Query<Category> theQuery = currentSession.createQuery("from Category order by name", 
																Category.class);
			
			// execute query and get result list
			List<Category> categories = theQuery.getResultList();
			
			
			// return the results
			return categories;
		}


		public void saveCategory(Category category) {
		
			Session currentSession = sessionFactory.getCurrentSession();
			currentSession.saveOrUpdate(category);
	}
		
		public int getPk(String name) {
			
			int id=0;
			Session currentSession = sessionFactory.getCurrentSession();
			Query<Category> theQuery = currentSession.createQuery("from Category WHERE name = :name", Category.class);
			theQuery.setParameter("name",name);
			Category category = (Category) theQuery.setMaxResults(1).uniqueResult();
			if(category==null) {
				id=0; }
			else {
				id = category.getId(); }
			return id;
		}
		
		
		public Category getCategory(int theId) {
			Session currentSession = sessionFactory.getCurrentSession();
			Category theCategory = currentSession.get(Category.class,theId);
			return theCategory;
			
		}
}
