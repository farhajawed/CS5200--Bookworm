package com.bookstoremanagement.dao;

import java.util.List;

import com.bookstoremanagement.model.WishList;

public interface WishListDao{
		
	/**
	 * @param wl
	 * Saves the wishlist in database.
	 */
	public void saveInWishList(WishList wl);
	/**
	 * @param id
	 * deletes the details of the wishlist for given id. 
	 */
	public void deleteFromWishList(int id);
	
	/**
	 * @param id
	 * @return the wishlist for the given userId.
	 */
	public List<WishList> fetchWishListByUser(int id);
	
}
