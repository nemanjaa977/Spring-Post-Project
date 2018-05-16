package com.nemanja97.Projectpost.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.nemanja97.Projectpost.entity.User;
import com.nemanja97.Projectpost.repository.UserRepository;

@Service
public class UserService implements UserServiceInterface {
	
	@Autowired
	UserRepository userRepository;
	
	@Override
	public User findOne(int userId) {
		return userRepository.getOne(userId);
	}
	
	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}
	
	@Override 
	public User save(User user) {
		return userRepository.save(user);
	}
	
	@Override
	public void remove(int id) {
		userRepository.deleteById(id);
	}

	@Override
	public User findByUsername(String username) {

		return userRepository.findByUsername(username);
	}
	
	

}
