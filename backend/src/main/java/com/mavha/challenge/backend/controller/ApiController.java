package com.mavha.challenge.backend.controller;

import java.util.HashMap;
import java.util.NoSuchElementException;

import org.hibernate.PropertyValueException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.mavha.challenge.backend.helper.Response;
import com.mavha.challenge.backend.helper.TokenAuthenticator;
import com.mavha.challenge.backend.model.Checkout;
import com.mavha.challenge.backend.model.Confirmation;
import com.mavha.challenge.backend.model.Listing;
import com.mavha.challenge.backend.model.SpecialPrice;
import com.mavha.challenge.backend.service.ListingService;
import com.mavha.challenge.backend.service.ServiceException;
import com.mavha.challenge.backend.service.SpecialPriceService;

@RestController
@RequestMapping("api/listings")
public class ApiController {
    
	@Autowired
	private ListingService listingService;
	
	@Autowired
	private SpecialPriceService specialPriceService;
	
	//CRUD of Listings
	
	@PostMapping
	public ResponseEntity<?> createListing(@RequestBody Listing listing, @RequestHeader("Authorization") String token) {
		try {
			String idOwner = TokenAuthenticator.decode(token);
			if(idOwner != null && idOwner != "") {
				return ResponseEntity.ok(listingService.save(listing,TokenAuthenticator.decode(token)));				
			}else {
				return ResponseEntity.badRequest().body(getResponseError(409,Response.TOKEN_NOT_VALIDATE));
			}
		} catch (DataIntegrityViolationException | PropertyValueException | NullPointerException exc){
			return ResponseEntity.badRequest().body(getResponseError(402,Response.VALUE_NOT_NULL));
		} catch (ServiceException e) {
			return ResponseEntity.badRequest().body(e.getResponseError());
		}
	}
	
	@GetMapping
	public ResponseEntity<?> getListings(){
		try {
			return ResponseEntity.ok(listingService.getList());
		} catch (NoSuchElementException e) {
			return ResponseEntity.badRequest().body(getResponseError(403,Response.NOT_VALUE));
		}catch (ServiceException ex) {
			return ResponseEntity.badRequest().body(ex.getResponseError());
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getListing(@PathVariable String id){
		try {
			return ResponseEntity.ok(listingService.get(id));
		} catch (NoSuchElementException e) {
			return ResponseEntity.badRequest().body(getResponseError(403,Response.NOT_VALUE));
		} catch (ServiceException e) {
			return ResponseEntity.badRequest().body(e.getResponseError());
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> updateListing(@PathVariable String id, 
			@RequestBody Listing listing, @RequestHeader("Authorization") String token){
		try {
			return ResponseEntity.ok(listingService.update(id,listing,TokenAuthenticator.decode(token)));				
		} catch (DataIntegrityViolationException exc){
			return ResponseEntity.badRequest().body(getResponseError(402,Response.VALUE_NOT_NULL));
		} catch(PropertyValueException ex) {
			return ResponseEntity.badRequest().body(getResponseError(402,Response.VALUE_NOT_NULL));
		} catch (NoSuchElementException e) {
			return ResponseEntity.badRequest().body(getResponseError(403,Response.NOT_VALUE));
		} catch (ServiceException e) {
			return ResponseEntity.badRequest().body(e.getResponseError());
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteListing(@PathVariable String id, @RequestHeader("Authorization") String token){
		try {
			listingService.delete(id,TokenAuthenticator.decode(token));
			HashMap<String,String> response = new HashMap<>();
			response.put("id", id);
			return ResponseEntity.ok(response);	
		} catch (EmptyResultDataAccessException e) {
			return ResponseEntity.badRequest().body(getResponseError(403,Response.DELETE_NOT_EXITS));
		} catch (ServiceException e) {
			return ResponseEntity.badRequest().body(e.getResponseError());
		}
		
	}
	
	//Special Prices CRUD
	
	@PostMapping("/{id}/special-prices")
	public ResponseEntity<?> createSpecialPrice(@PathVariable String id,@RequestBody SpecialPrice specialPrice){
		try {
			return ResponseEntity.ok(specialPriceService.save(id, specialPrice));
		} catch (DataIntegrityViolationException exc){
			return ResponseEntity.badRequest().body(getResponseError(402,Response.VALUE_NOT_NULL));
		} catch(PropertyValueException ex) {
			return ResponseEntity.badRequest().body(getResponseError(402,Response.VALUE_NOT_NULL));
		} catch (ServiceException exce) {
			return ResponseEntity.badRequest().body(exce.getResponseError());
		}
	}
	
	//Calculate of cost
	
	 @PostMapping("/{id}/reservation-cost")
	 public ResponseEntity<?> getCost(@PathVariable String id, @RequestBody Checkout checkout){
		 try {
			return ResponseEntity.ok(listingService.getCost(id, checkout));
		} catch (ServiceException e) {
			return ResponseEntity.badRequest().body(e.getResponseError());
		}				 					 
	 }
	 
	 //Confirm reservation
	 
	 @PostMapping("/{id}/confirmation-reservation")
	 public ResponseEntity<?> saveConfirmation(@PathVariable String id, @RequestBody Confirmation confirmation){
		 try {
			return ResponseEntity.ok(listingService.saveConfirmation(id, confirmation));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().body(getResponseError(400, Response.ERROR));
		}				 					 
	 }
	 
	 //Helper
	 
	 private HashMap<String,Object> getResponseError(int error, String message){
		 HashMap<String,Object> response = new HashMap<>();
		 response.put("error", error);
		 response.put("message", message);
		 return response;
	 }
	
}
