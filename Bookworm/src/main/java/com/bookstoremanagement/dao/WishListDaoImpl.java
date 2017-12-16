package com.bookstoremanagement.dao;

import java.sql.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bookstoremanagement.model.WishList;

@Repository("wishListDao")
@Transactional
public class WishListDaoImpl implements WishListDao {

	@Autowired
	SessionFactory sessionFactory;
	
	public void saveInWishList(WishList wl) {
		try {
			Session currentSession = sessionFactory.getCurrentSession();
			wl.setCreatedOn(new Date(System.currentTimeMillis()));
			currentSession.save(wl);
		}catch(Exception e) {
			e.printStackTrace();
		}

	}

	public void deleteFromWishList(int id) {
		try {
			// get the current hibernate session
			Session currentSession = sessionFactory.getCurrentSession();
			// delete object with primary key
			Query theQuery = currentSession.createQuery("DELETE WishList WHERE id = :id)");
			theQuery.setParameter("id", id);
			theQuery.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public List<WishList> fetchWishListByUser(int id) {
		List<WishList> wishList = null;
		try {
			// get the current hibernate session
			Session currentSession = sessionFactory.getCurrentSession();
			// create a query
			Query<WishList> theQuery = currentSession.createQuery("FROM WishList WHERE userdetails =:id", WishList.class);
			theQuery.setParameter("id", id);
			wishList = theQuery.getResultList();
		} catch (Exception e) {
			return wishList;
		}
		return wishList;
	}

}
