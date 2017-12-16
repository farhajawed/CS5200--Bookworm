package com.bookstoremanagement.dao;

import java.sql.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bookstoremanagement.model.OrderBookDetails;
import com.bookstoremanagement.model.OrderDetails;
import com.bookstoremanagement.model.UserInfo;

@Repository("orderDetailsDao")
@Transactional
public class OrderDetailsDaoImpl implements OrderDetailsDao{

	@Autowired
	private SessionFactory sessionFactory;

	public void deleteOrderById(int id) {
		try {
			// get the current hibernate session
			Session currentSession = sessionFactory.getCurrentSession();
			// delete object with primary key
			Query theQuery = currentSession.createQuery("DELETE OrderDetails WHERE id = :id)");
			theQuery.setParameter("id", id);
			theQuery.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public List<OrderDetails> fetchAllOrders() {
		List<OrderDetails>  orders = null;
		try {
			// get the current hibernate session
			Session currentSession = sessionFactory.getCurrentSession();
			Query<OrderDetails> theQuery = currentSession.createQuery("FROM OrderDetails", OrderDetails.class);
			orders =  theQuery.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			return orders;
		}
		return orders;
	}

	public List<OrderDetails> fetchOrderByUser(UserInfo user) {
		Session currentSession = sessionFactory.getCurrentSession();
		// create a query
		Query<OrderDetails> theQuery = currentSession.createQuery("FROM OrderDetails "
				+ "WHERE users = :userId", OrderDetails.class);
		theQuery.setParameter("userId",user.getId());
		// execute query and get result list
		List<OrderDetails> orderDetails = theQuery.getResultList();
		// return the results
		return orderDetails;
	}
	public List<OrderDetails> fetchOrderByUserAndStatus(UserInfo user, String payStatus) {
		Session currentSession = sessionFactory.getCurrentSession();
		// create a query
		Query<OrderDetails> theQuery = currentSession.createQuery("FROM OrderDetails "
				+ "WHERE userdetails = :username and paystatus =:paystatus", OrderDetails.class);
		theQuery.setParameter("username",user);
		theQuery.setParameter("paystatus",payStatus);
		// execute query and get result list
		List<OrderDetails> orderDetails = theQuery.getResultList();
		// return the results
		return orderDetails;
	}

	public int saveOrder(OrderDetails order) {
		try {
			Session currentSession = sessionFactory.getCurrentSession();
			order.setCreatedOn(new Date(System.currentTimeMillis()));
			return (Integer) currentSession.save(order);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public OrderDetails fetchByOrderId(int id) {
		OrderDetails orderDetails = null;
		try {
			// get the current hibernate session
			Session currentSession = sessionFactory.getCurrentSession();
			orderDetails =  currentSession.get(OrderDetails.class, id); 
		} catch (Exception e) {
			e.printStackTrace();
			return orderDetails;
		}
		return orderDetails;
	}

	public List<OrderBookDetails> fetchOrderBookDetailsById(int id) {
		List<OrderBookDetails> cart = null;
		try {
			// get the current hibernate session
			Session currentSession = sessionFactory.getCurrentSession();
			// create a query
			Query<OrderBookDetails> theQuery = currentSession.createQuery("FROM OrderBookDetails WHERE orderdetails.id = :id", OrderBookDetails.class);
			theQuery.setParameter("id",id);
			cart = theQuery.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			return cart;
		}
		return cart;
	}

	public void deleteBookFromOrderById(int id) {
		try {
			// get the current hibernate session
			Session currentSession = sessionFactory.getCurrentSession();
			Query theQuery = currentSession.createQuery("DELETE OrderBookDetails WHERE id = :id");
			theQuery.setParameter("id", id);
			theQuery.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public OrderBookDetails fetchOrderBookDetailsByPk(int id) {
		OrderBookDetails orderBookDetails = null;
		try {
			Session currentSession = sessionFactory.getCurrentSession();
			orderBookDetails =  currentSession.get(OrderBookDetails.class, id); 
		} catch (Exception e) {
			e.printStackTrace();
			return orderBookDetails;
		}
		return orderBookDetails;
	}

	public void updateOrderDetails(OrderDetails orderDetails) {
		try {
			Session currentSession = sessionFactory.getCurrentSession();
			orderDetails.setUpdatedOn(new Date(System.currentTimeMillis()));
			currentSession.update(orderDetails);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void saveOrUpdateOrder(OrderDetails order) {
		try {
			Session currentSession = sessionFactory.getCurrentSession();
			if(order.getId()== 0)
				order.setCreatedOn(new Date(System.currentTimeMillis()));
			order.setUpdatedOn(new Date(System.currentTimeMillis()));
			currentSession.saveOrUpdate(order);
		}catch(Exception e) {
			e.printStackTrace();
		}

	}

}
