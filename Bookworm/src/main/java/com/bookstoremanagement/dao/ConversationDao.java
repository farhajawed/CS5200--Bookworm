package com.bookstoremanagement.dao;

import java.util.List;

import com.bookstoremanagement.model.Conversation;

public interface ConversationDao {
	public void saveConversation(Conversation conversation);
	public List<Object> list(String username);
	public void deleteConversation(int id);
	public Conversation getConversation(int id);
}
