package com.bookstore.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bookstore.entity.Users;

import jakarta.persistence.EntityManager;

public class UserDAO extends JpaDAO<Users> implements GenericDAO<Users> {
	
	public UserDAO() {
		super();
	}
	
	@Override
	public Users create(Users user) {
		return super.create(user);
	}

	@Override
	public Users update(Users user) {
		return super.update(user);
	}

	@Override
	public Users get(Object userId) {
		return super.find(Users.class, userId);
	}

	@Override
	public void delete(Object userId) {
		super.delete(Users.class, userId);
	}

	@Override
	public List<Users> listAll() {
		return super.findWithNameQuery("Users.findAll");
	}

	@Override
	public long count() {
		return super.countWithNameQuery("Users.countAll");
	}
	
	
	public Users findByEmail(String email) {
		List<Users> result = super.findWithNameQuery("Users.findByEmail", "email", email);
		if (!result.isEmpty()) {
			return result.get(0);
		}
		
		return null;
	}
	
	public boolean checkLogin(String email, String password) {
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("email", email);
		parameters.put("password", password);
		
		List<Users> listUsers = super.findWithNameQuery("Users.checkLogin", parameters);
		
		if (listUsers.size() == 1) {
			return true;
		}
		return false;
	}
}
