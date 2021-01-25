package com.mavha.challenge.backend.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mavha.challenge.backend.model.SpecialPrice;

@Repository
public interface SpecialPriceRepository extends CrudRepository<SpecialPrice, String> {

	
}
