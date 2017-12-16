package com.bookstoremanagement.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;

import com.bookstoremanagement.PO.RegistrationPO;

@Entity
@Table(name="users")
public class UserLogin {


	public UserLogin() {
	}
	
	public UserLogin(UserInfo userInfo2) {
		this.userInfo = userInfo2;
	}
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@NotEmpty
	@NotBlank(groups = {RegistrationPO.class})
	@Size(min = 4, max = 20)
	@Column(name="username")
	private String username;
	
	@NotEmpty
	@NotBlank(groups = {RegistrationPO.class})
	@Size(min = 4, max = 20)
	@Column(name="password")
	private String password;

	@Column(name="userrole")
	private String userRole;

	@Column(name="enabled")
	private int enabled;

	@Column(name="created")
	private Date createdOn;
	
	@Column(name="updated")
	private Date updatedOn;
	
	@OneToOne(mappedBy = "userLogin") 
	@Cascade({CascadeType.ALL})
	private UserInfo userInfo;


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

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getEnabled() {
		return enabled;
	}
	public void setEnabled(int enabled) {
		this.enabled = enabled;
	}
	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public Date getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}

	public String getUserRole() {
		return userRole;
	}
	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}
	public UserInfo getUserInfo() {
		return userInfo;
	}
	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}
	@Override
	public String toString() {
		return "UserLogin [id=" + id + ", username=" + username + ", password=" + password + ", userRole=" + userRole
				+ ", enabled=" + enabled + ", userInfo=" + userInfo + "]";
	}
}
