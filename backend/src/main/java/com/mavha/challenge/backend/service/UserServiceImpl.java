package com.mavha.challenge.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mavha.challenge.backend.model.User;
import com.mavha.challenge.backend.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public User save(User user) throws ServiceException {
		return userRepository.save(user);
	}
	
	@Override
	public User getByEmail(String email) throws ServiceException {
		return userRepository.findByEmail(email).get();
		
	}

}
