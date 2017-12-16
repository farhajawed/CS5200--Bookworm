package com.bookstoremanagement.dao;

import java.util.List;

import com.bookstoremanagement.model.OrderBookDetails;
import com.bookstoremanagement.model.OrderDetails;
import com.bookstoremanagement.model.UserInfo;

public interface OrderDetailsDao {
	/**
	 * @param order
	 * @return the id of the order details of the given order after saving it in database.
	 */
	public int saveOrder(OrderDetails order);


	/**
	 * @param order
	 * Updates the details of given order in database.
	 */
	public void updateOrderDetails(OrderDetails order);

	/**
	 * @param order
	 * Saves or updates the details of given order details in database.
	 */
	
	public void saveOrUpdateOrder(OrderDetails order);

	/**
	 * @param id
	 * Deletes the order for given id in database.
	 */
	public void deleteOrderById(int id);
	/**
	 * @param id
	 * Removes the book added in cart for an order, using given id. 
	 */
	public void deleteBookFromOrderById(int id);


	/**
	 * @param id
	 * @return the order details for given id.
	 */
	public OrderDetails fetchByOrderId(int id);


	/**
	 * @param user
	 * @return all the order details for a given user.
	 */
	public List<OrderDetails> fetchOrderByUser(UserInfo user);


	/**
	 * @param user
	 * @param payStatus
	 * @return the order details for the given user for a specific payStatus.
	 * payStatus - ['COMPLETED','IN PROCESS','PENDING','SCHEDULED']
	 */
	public List<OrderDetails> fetchOrderByUserAndStatus(UserInfo user, String payStatus);
	
	/**
	 * @param id
	 * @return the details of the seller and book in cart for given id.
	 */
	public OrderBookDetails fetchOrderBookDetailsByPk(int id);
	
	/**
	 * @param id
	 * @return the details of seller(s) and book(s) in cart for given order id.
	 */
	public List<OrderBookDetails> fetchOrderBookDetailsById(int id);

}
