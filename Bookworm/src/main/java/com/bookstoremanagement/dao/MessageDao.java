package com.bookstoremanagement.dao;

import java.util.List;

import com.bookstoremanagement.model.Messages;

public interface MessageDao {

	public void saveMessage(Messages message);
	public List<Messages> list(int id);
}
