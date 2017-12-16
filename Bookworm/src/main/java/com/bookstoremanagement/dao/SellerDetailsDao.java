package com.bookstoremanagement.dao;

import java.util.List;

import com.bookstoremanagement.model.SellerDetails;

public interface SellerDetailsDao {
	
	public void saveSellerDetails(SellerDetails sellerDetails);
	public void saveAllSellerDetails(SellerDetails sellerDetails);
	public List<Object> list(String term);
	public List<SellerDetails> listBooksOfSellerByUsername(String name);
	public SellerDetails getSellerDetails(int theId);
	public void deleteSellerDetails(int theId);
	public List<Object> listByAuthor(String authorName);
	public List<Object> listByCategory(String categoryName);
}
