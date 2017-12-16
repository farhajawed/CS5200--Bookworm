package com.bookstoremanagement.dao;

import java.util.List;

import com.bookstoremanagement.model.Category;

public interface CategoryDao {
	
	public void saveCategory(Category author);
	
	public int getPk(String name);
	
	public Category getCategory(int theId);
	
	public List<Category> getCategory();
}
