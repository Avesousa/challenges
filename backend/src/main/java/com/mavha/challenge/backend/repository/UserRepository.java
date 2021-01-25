package com.mavha.challenge.backend.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mavha.challenge.backend.model.User;

@Repository
public interface UserRepository extends CrudRepository<User,String>{

	public Optional<User> findByEmail(String email);
	
}
