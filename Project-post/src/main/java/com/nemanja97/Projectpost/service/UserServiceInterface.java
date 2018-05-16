package com.nemanja97.Projectpost.service;

import java.util.List;

import com.nemanja97.Projectpost.entity.User;

public interface UserServiceInterface {
	
	User findOne(int id);
	List<User> findAll();
	User save(User user);
	void remove(int id);
	User findByUsername(String username);
}
