package com.mavha.challenge.backend.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mavha.challenge.backend.model.Listing;

@Repository
public interface ListingRepository extends CrudRepository<Listing,String>{

	public Optional<Listing> findById(String id);
	
}
