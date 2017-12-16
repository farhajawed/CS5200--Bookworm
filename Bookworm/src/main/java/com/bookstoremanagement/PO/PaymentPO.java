package com.bookstoremanagement.PO;

import com.bookstoremanagement.model.UserLogin;

public class PaymentPO {
	
	private UserLogin user;
	private float amount;
	
	public UserLogin getUser() {
		return user;
	}
	public void setUser(UserLogin user) {
		this.user = user;
	}
	public float getAmount() {
		return amount;
	}
	public void setAmount(float amount) {
		this.amount = amount;
	}
}
