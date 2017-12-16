package com.bookstoremanagement.dao;

import java.util.List;

import com.bookstoremanagement.model.UserLogin;

public interface UserDao {

	/**
	 * @param username
	 * @return the details of the user with given username.
	 */
	public UserLogin findUserInfoByUsername(String username);
	
	/**
	 * @param admin
	 * @return the details of given name except the logged in user with the role of ADMIN.
	 */
	public List<UserLogin> fetchGivenUsersExceptAdmin(UserLogin admin, String name);
	
	/**
	 * @param user
	 * Saves the details of the given user in database.
	 */
	public void register(UserLogin user);
	/**
	 * @param id
	 * @return the details of the user with given id.
	 */
	public UserLogin fetchUser(int id);
	
	/**
	 * @param user
	 * updates the information of the given user in database.
	 */
	public void updateUserDetails(UserLogin user);
	
	
	/**
	 * @param user
	 * Deletes the given user from the database.
	 */
	public void deleteUser(UserLogin user);
}