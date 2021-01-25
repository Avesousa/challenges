package com.mavha.challenge.backend.service;

import com.mavha.challenge.backend.model.User;

public interface UserService {

	public User save(User user) throws ServiceException;
	public User getByEmail(String email) throws ServiceException;
	
}
