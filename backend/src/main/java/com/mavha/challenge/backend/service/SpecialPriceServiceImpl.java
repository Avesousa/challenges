package com.mavha.challenge.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mavha.challenge.backend.model.Listing;
import com.mavha.challenge.backend.model.SpecialPrice;
import com.mavha.challenge.backend.repository.ListingRepository;
import com.mavha.challenge.backend.repository.SpecialPriceRepository;

@Service
public class SpecialPriceServiceImpl implements SpecialPriceService{

	@Autowired
	private ListingRepository listingRepository;
	
	@Autowired
	private SpecialPriceRepository specialPriceRepository;
	
	@Override
	public SpecialPrice save(String idListing, SpecialPrice specialPrice) throws ServiceException {
		Listing listing = listingRepository.findById(idListing).get();
		specialPrice.setListing(listing);
		SpecialPrice specialPriceWithId = specialPriceRepository.save(specialPrice);
		return specialPriceWithId;
	}

	@Override
	public void delete(String idListing, String idSpecialPrice) {
		specialPriceRepository.deleteById(idSpecialPrice);		
	}
	
	

}
