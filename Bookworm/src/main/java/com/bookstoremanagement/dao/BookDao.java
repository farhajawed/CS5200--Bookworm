package com.bookstoremanagement.dao;

import java.util.List;

import com.bookstoremanagement.model.Book;


public interface BookDao {
	//search books by isbn or title
	List<Book> list(String term);
	Book searchByIsbn(String isbn);
	int getPk(String isbn);
	void saveBook(Book book);
	Book getBook(int theId);
}
