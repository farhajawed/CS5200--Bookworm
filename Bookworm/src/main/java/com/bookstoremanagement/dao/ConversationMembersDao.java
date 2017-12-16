package com.bookstoremanagement.dao;

import com.bookstoremanagement.model.ConversationMembers;

public interface ConversationMembersDao {
	
	public void saveConversationMember(ConversationMembers conversationMember);
	public ConversationMembers searchByConversationIdAndUsername(String username,int id);
	public ConversationMembers searchByConversationIdAndCurrentUsername(String username,int id);
	
}
