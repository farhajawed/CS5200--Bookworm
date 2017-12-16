package com.bookstoremanagement.dao;

import java.util.List;

import com.bookstoremanagement.model.Rating;

public interface RatingDao {
	public void saveRating(Rating rating);
	public Rating searchByIsbnUserName(String isbn, String username);
	public List<Rating> getRatings(String isbn);
	public Object getAverageRating(String isbn);
}
