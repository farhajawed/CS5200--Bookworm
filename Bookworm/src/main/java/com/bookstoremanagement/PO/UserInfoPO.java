package com.bookstoremanagement.PO;

import java.io.File;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.bookstoremanagement.model.OrderDetails;
import com.bookstoremanagement.model.UserAddress;
import com.bookstoremanagement.model.UserInfo;
import com.bookstoremanagement.model.UserLogin;

public class UserInfoPO {

	public UserInfoPO() {
	}

	public UserInfoPO(int id, String username, String firstname, String lastname, String email, int phone) {
		super();
		this.id = id;
		this.username = username;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.phone = phone;
	}

	public UserInfoPO(UserInfo user) {
		super();
		this.id = user.getId();
		this.firstname = user.getFirstname();
		this.lastname = user.getLastname();
		this.email = user.getEmail();
		this.phone = user.getPhone();
		this.dob = user.getDob();
		this.useraddress = user.getUseraddress();
		this.userLogin = user.getUserLogin();
		if(user.getUserLogin()!=null) {
			this.userrole = user.getUserLogin().getUserRole();
			this.username = user.getUserLogin().getUsername();
		}
		if(user.getImageUrl() != null && !user.getImageUrl().isEmpty())
			this.imageUrl = user.getImageUrl();
	}

	private int id;
	private String username;
	private String firstname;
	private String lastname;
	private String email;
	private Date dob;
	private int phone;
	private List<UserAddress> useraddress = new ArrayList<UserAddress>();
	private List<OrderDetails> orderdetails;
	private UserLogin userLogin;
	private String password;
	private String userrole;
	private File image;
	private String imageUrl;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public int getPhone() {
		return phone;
	}
	public void setPhone(int phone) {
		this.phone = phone;
	}

	public List<UserAddress> getUseraddress() {
		return useraddress;
	}

	public void setUseraddress(List<UserAddress> useraddress) {
		this.useraddress = useraddress;
	}

	public List<OrderDetails> getOrderdetails() {
		return orderdetails;
	}

	public void setOrderdetails(List<OrderDetails> orderdetails) {
		this.orderdetails = orderdetails;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserrole() {
		return userrole;
	}

	public void setUserrole(String userrole) {
		this.userrole = userrole;
	}

	public UserLogin getUserLogin() {
		return userLogin;
	}

	public void setUserLogin(UserLogin userLogin) {
		this.userLogin = userLogin;
	}

	public File getImage() {
		return image;
	}

	public void setImage(File image) {
		this.image = image;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	@Override
	public String toString() {
		return "UserInfo [username=" + username + ", firstname=" + firstname + ", lastname="
				+ lastname + ", email=" + email + ", dob=" + dob + ", phone=" + phone  + ", useraddress="
				+ useraddress + "]";
	}

}