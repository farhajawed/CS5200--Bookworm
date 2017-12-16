package com.bookstoremanagement.dao;

import java.sql.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bookstoremanagement.model.UserLogin;

@Repository("userDao")
@Transactional
public class UserDaoImpl implements UserDao {

	@Autowired
	private SessionFactory sessionFactory;

	public UserLogin findUserInfoByUsername(String username) {
		UserLogin userLoginInfo = null;
		try {
			// get the current hibernate session
			Session currentSession = sessionFactory.getCurrentSession();
			Query<UserLogin> theQuery = currentSession.createQuery("FROM UserLogin WHERE username = :username", UserLogin.class);
			theQuery.setParameter("username",username);
			userLoginInfo = theQuery.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
			return userLoginInfo;
		}
		return userLoginInfo;
	}

	public UserLogin findUserInfo(int id) {
		UserLogin userLoginInfo = null;
		try {
			// get the current hibernate session
			Session currentSession = sessionFactory.getCurrentSession();
			Query<UserLogin> theQuery = currentSession.createQuery("FROM UserLogin WHERE id = :id and enabled = 1", UserLogin.class);
			theQuery.setParameter("id",id);
			userLoginInfo = theQuery.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
			return userLoginInfo;
		}
		return userLoginInfo;
	}

	public void register(UserLogin user) {
		try {
			// get the current hibernate session
			Session currentSession = sessionFactory.getCurrentSession();
			user.setCreatedOn(new Date(System.currentTimeMillis()));
			currentSession.save(user);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public UserLogin fetchUser(int id) {
		UserLogin user = null;
		try {
			// get the current hibernate session
			Session currentSession = sessionFactory.getCurrentSession();
			user =  currentSession.get(UserLogin.class, id); 
		} catch (Exception e) {
			e.printStackTrace();
			return user;
		}
		return user;
	}

	public void updateUserDetails(UserLogin updatedUser) {
		try {
			Session currentSession = sessionFactory.getCurrentSession();
			updatedUser.setUpdatedOn(new Date(System.currentTimeMillis()));
			currentSession.update(updatedUser); 
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	public List<UserLogin> fetchGivenUsersExceptAdmin(UserLogin admin,String name) {
		List<UserLogin> users = null;
		try {
			// get the current hibernate session
			Session currentSession = sessionFactory.getCurrentSession();
			// create a query
			Query theQuery = currentSession.createQuery("FROM UserLogin user WHERE user.id != :id and user.username =:name or user.userInfo.firstname =:name");
			if(name.isEmpty())
				theQuery = currentSession.createQuery("FROM UserLogin user WHERE user.id != :id");
			theQuery.setParameter("id", admin.getId());
			if(!name.isEmpty())
			theQuery.setParameter("name", name);
			users = theQuery.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			return users;
		}
		return users;
	}

	public void deleteUser(UserLogin user) {
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		// delete object with primary key
		Query theQuery = currentSession.createQuery("DELETE UserLogin WHERE id = :id");
		theQuery.setParameter("id", user.getId());
		theQuery.executeUpdate();
	}

}