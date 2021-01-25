package com.mavha.challenge.backend.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mavha.challenge.backend.model.Confirmation;

@Repository
public interface ConfirmationRepository extends CrudRepository<Confirmation,String>{	
}
