package com.bookstoremanagement.dao;

import java.util.List;

import com.bookstoremanagement.model.Author;

public interface AuthorDao {
	
	public void saveAuthor(Author author);
	
	public int getPk(String name);
	
	public Author getAuthor(int theId);
	
	public List<Author> getAuthors();
}
