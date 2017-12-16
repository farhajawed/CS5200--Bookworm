package com.bookstoremanagement.utilities;

public class Constants {

	public static final String REGISTRATION_TECH_ERROR = "Some technical issue occured, unable to register at the moment.  \n Please try again after some time.";
	public static final String PROFILE_TECH_ERROR = "Some technical issue occured, unable to load profile at the moment.  \n Please try again after some time.";
	public static final String USER_PROFILE_TECH_ERROR = "Some technical issue occured, unable to load selected user's profile at the moment.  \n Please try again after some time.";
	public static final String PROFILE_EDIT_TECH_ERROR = "Some technical issue occured, unable to edit profile details at the moment.  \n Please try again after some time.";
	public static final String PROFILE_EDIT_SAVE_TECH_ERROR = "Some technical issue occured, unable to save profile changes at the moment. Please try again after some time.";
	public static final String PROFILE_DELETE_TECH_ERROR = "Some technical issue occured, unable to delete user account at the moment.  \nPlease try again after some time.";
	public static final String ORDER_HISTORY_TECH_ERROR = "Some technical issue occured, unable to load order history at the moment.  \n Please try again after some time.";
	public static final String SALES_HISTORY_TECH_ERROR = "Some technical issue occured, unable to load sales history at the moment.  \n Please try again after some time.";
	public static final String PASSWORD_CHANGE_TECH_ERROR = "Some technical issue occured, unable to change password at the moment.  \n Please try again after some time.";
	public static final String PASSWORD_CHANGE_SAVE_TECH_ERROR = "Some technical issue occured, unable to save new password at the moment.  \n Please try again after some time.";
	public static final String PAYMENT_TECH_ERROR = "Some technical issue occured, unable to make payment at the moment.  \n Please try again after some time.";
	public static final String UNABLE_TO_RELOAD_TECH_ERROR = "Some technical issue occured, unable to go back at the moment.  \n Please try again after some time.";
	public static final String UNABLE_TO_LOAD_TECH_ERROR = "Some technical issue occured, unable to load at the moment.  \n Please try again after some time.";
	public static final String UNABLE_TO_LOAD_CART_TECH_ERROR = "Some technical issue occured, unable to load wishlist at the moment.  \n Please try again after some time.";
	public static final String UNABLE_TO_LOAD_WISHLIST_TECH_ERROR = "Some technical issue occured, unable to load cart at the moment.  \n Please try again after some time.";
	public static final String ORDER_DELETE_TECH_ERROR = "Some technical issue occured, unable to remove selected book from order at the moment. \n  Please try again after some time.";
	public static final String WISHLIST_DELETE_TECH_ERROR = "Some technical issue occured, unable to remove selected book from wishlist at the moment. Please try again after some time.";



	public static final String ACCESS_DENIED = "You do not have permission to access this page!";
	public static final String GENERIC_ERROR = "Some technical issue occured. Please try again.";
	public static final int ENABLE_USER_ACCOUNT = 1;
	public static final int DISABLE_USER_ACCOUNT = 0;
	public static final String PAYSTATUS_PENDING = "PENDING";
	public static final String PAYSTATUS_COMPLETED = "COMPLETED";
	public static final String MODE_OF_PAY_CASH = "CASH";
	public static final String ISPRIMARY = "Y";
	public static final String ADMIN_ROLE = "ADMIN";
	public static final String PRIME_ROLE = "PRIME";
	public static final float DISCOUNT_PERCENT = 0.05f;
	public static final String VIEW_ALL_USERS = "viewAllUser";
	public static final String SEARCH_USER = "searchUser";
	public static final String USERNAME_ALREADY_IN_USE = "AlreadyInUse.username";

	public static String copiesUnavailableMessage(int noOfCopies, String bookTitle) {
		if(bookTitle.isEmpty()) {
			return "Only "+noOfCopies+" books available.  \n Please select quantity accordingly.";
		}
		else
			return "Only "+noOfCopies + " copies of "+ bookTitle +" available. "
			+ " \n Please update quantity accordingly.";
	}

}
