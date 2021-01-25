package com.mavha.challenge.backend.service;

import java.util.HashMap;
import java.util.List;

import com.mavha.challenge.backend.model.Checkout;
import com.mavha.challenge.backend.model.Confirmation;
import com.mavha.challenge.backend.model.Cost;
import com.mavha.challenge.backend.model.Listing;

public interface ListingService {
	
	public Listing save(Listing listing, String idOwner) throws ServiceException;
	public List<Listing> getList() throws ServiceException;
	public Listing get(String id) throws ServiceException;
	public Listing update(String id, Listing listing, String owner) throws ServiceException;
	public void delete(String id, String owner) throws ServiceException;
	public HashMap<String,String> saveConfirmation(String id, Confirmation confirmation);
	
	// bussines
	
	public Cost getCost(String id, Checkout checkout) throws ServiceException;
	
}
