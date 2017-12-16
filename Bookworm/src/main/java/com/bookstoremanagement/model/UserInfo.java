package com.bookstoremanagement.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.bookstoremanagement.PO.RegistrationPO;
import com.bookstoremanagement.PO.UserInfoPO;

@Entity
@Table(name="userdetails")
public class UserInfo {

	public UserInfo() {
	}

	public UserInfo(int id, String firstname, String lastname, String email, int phone) {
		super();
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.phone = phone;
	}

	public UserInfo(UserInfoPO user) {
		super();
		this.id = user.getId();
		this.firstname = user.getFirstname();
		this.lastname = user.getLastname();
		this.email = user.getEmail();
		this.phone = user.getPhone();
		this.dob = user.getDob();
		this.useraddress = user.getUseraddress();
		if(user.getImage() != null)
			this.imageUrl = user.getImage().getAbsolutePath();
		else
			this.imageUrl = user.getImageUrl();
		this.userLogin = user.getUserLogin();
		if(user.getUserLogin() != null)
			this.userLogin.setUserRole(user.getUserrole());
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;

	@NotEmpty
	@NotBlank(groups = {RegistrationPO.class})
	@Size(min = 4, max = 20)
	@Column(name="firstname")
	private String firstname;

	@NotEmpty
	@NotBlank(groups = {RegistrationPO.class})
	@Size(min = 4, max = 20)
	@Column(name="lastname")
	private String lastname;

	@NotEmpty
	@Email(groups = {RegistrationPO.class})
	@Column(name="email")
	private String email;

	@NotEmpty
	@NotBlank(groups = {RegistrationPO.class})
	@Column(name="dob")
	private Date dob;

	@NotEmpty
	@NotBlank(groups = {RegistrationPO.class})
	@Column(name="phone")
	private int phone;

	@OneToMany(mappedBy="userdetails")
	@Cascade({CascadeType.ALL})
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<UserAddress> useraddress = new ArrayList<UserAddress>();

	@OneToOne
	@JoinColumn(name="id")
	private UserLogin userLogin;

//	@OneToMany
//	@JoinColumn(name="userdetails")
//	@Cascade(CascadeType.ALL)
//	@LazyCollection(LazyCollectionOption.FALSE)
//	private List<OrderDetails> orderdetails;
	
	@OneToMany(mappedBy="userdetails")
	@Cascade(CascadeType.ALL)
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<WishList> wishList;

	@Column(name="displaypicture")
	private String imageUrl;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public UserLogin getUserLogin() {
		return userLogin;
	}

	public void setUserLogin(UserLogin userLogin) {
		this.userLogin = userLogin;
		if(userLogin!=null)
			userLogin.setUserInfo(this);
	}

//	public List<OrderDetails> getOrderdetails() {
//		return orderdetails;
//	}
//
//	public void setOrderdetails(List<OrderDetails> orderdetails) {
//		this.orderdetails = orderdetails;
//	}

	public List<WishList> getWishList() {
		return wishList;
	}

	public void setWishList(List<WishList> wishList) {
		this.wishList = wishList;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	@Override
	public String toString() {
		return "UserInfo [id=" + id + ", firstname=" + firstname + ", lastname="
				+ lastname + ", email=" + email + ", dob=" + dob + ", phone=" + phone +"]";
	}

	public void addAddressDetails(UserAddress userAdd) {
		if (userAdd == null) {
			return;
		}
		userAdd.setUserdetails(this);
		if (useraddress == null) {
			useraddress = new ArrayList<UserAddress>();
			useraddress.add(userAdd);
		} else if (!useraddress.contains(userAdd)) {
			useraddress.add(userAdd);
		}
	}
	
//	public void addToOrderDetails(OrderDetails order) {
//		if (order == null) {
//			return;
//		}
//		order.setUserdetails(this);
//		if (this.orderdetails == null) {
//			orderdetails = new ArrayList<OrderDetails>();
//			orderdetails.add(order);
//		} else if (!orderdetails.contains(order)) {
//			orderdetails.add(order);
//		}
//	}



}