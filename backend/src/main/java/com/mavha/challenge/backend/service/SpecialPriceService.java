package com.mavha.challenge.backend.service;

import com.mavha.challenge.backend.model.SpecialPrice;

public interface SpecialPriceService {

	public SpecialPrice save(String idListing, SpecialPrice specialPrice) throws ServiceException;
	public void delete(String idListing, String idSpecialPrice) throws ServiceException;
	
}
