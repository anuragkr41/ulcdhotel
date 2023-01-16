package com.ulcd.user.service.UserService.services.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ulcd.user.service.UserService.entities.User;
import com.ulcd.user.service.UserService.exceptions.ResourceNotFoundException;
import com.ulcd.user.service.UserService.repositories.UserRepository;
import com.ulcd.user.service.UserService.services.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public User saveUser(User user) {
		// generate unique user Id
		
		String randomUserId = UUID.randomUUID().toString();
		user.setUserId(randomUserId);
		return userRepository.save(user);
	}

	@Override
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public User getUser(String userId) {
		return userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User with the given id is not found on server !! " + userId));
	}

}
