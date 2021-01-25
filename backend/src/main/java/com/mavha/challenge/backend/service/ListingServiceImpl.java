package com.mavha.challenge.backend.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mavha.challenge.backend.helper.FormatDate;
import com.mavha.challenge.backend.helper.Response;
import com.mavha.challenge.backend.model.Checkout;
import com.mavha.challenge.backend.model.Confirmation;
import com.mavha.challenge.backend.model.Cost;
import com.mavha.challenge.backend.model.Listing;
import com.mavha.challenge.backend.model.SpecialPrice;
import com.mavha.challenge.backend.model.User;
import com.mavha.challenge.backend.repository.ConfirmationRepository;
import com.mavha.challenge.backend.repository.ListingRepository;
import com.mavha.challenge.backend.repository.UserRepository;

@Service
public class ListingServiceImpl implements ListingService{

	@Autowired
	private ListingRepository listingRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ConfirmationRepository confirmationRepository;
	
	@Override
	public Listing save(Listing listing, String idOwner) throws ServiceException {
		try {
			System.out.println(idOwner);
			User user = userRepository.findById(idOwner).get();					
			listing.setUser(user);
			listing.setSlug(listing.getName().toLowerCase());
			listing.setSpecialPrice(new ArrayList<SpecialPrice>());
			return listingRepository.save(listing);	
		} catch (NoSuchElementException e) {
			throw new ServiceException(409,Response.TOKEN_NOT_VALIDATE);
		}
	}

	@Override
	public List<Listing> getList() {
		List<Listing> listings = new ArrayList<Listing>();
		listingRepository.findAll().forEach(listing -> listings.add(listing));
		return listings;
	}

	@Override
	public Listing get(String id) {
		return listingRepository.findById(id).get();
	}

	@Override
	public Listing update(String id, Listing listing, String owner) throws ServiceException {
		Listing oldListing = listingRepository.findById(id).get();
		if(owner.equals(oldListing.getUser())) {
			listing.setId(oldListing.getId());
			listing.setSlug(listing.getName().toLowerCase());
			listing.setSpecialPrice(oldListing.getSpecialPrice());
			listing.setUser(userRepository.findById(oldListing.getUser()).get());
			return listingRepository.save(listing);
		}else {
			throw new ServiceException(409,Response.TOKEN_NOT_VALIDATE);
		}
	}

	@Override
	public void delete(String id, String owner) throws ServiceException {
		Listing listing = listingRepository.findById(id).get();
		if(owner.equals(listing.getUser())) {
			listingRepository.deleteById(id);				
		}else {
			throw new ServiceException(409,Response.TOKEN_NOT_VALIDATE);
		}
	}

	@Override
	public Cost getCost(String id, Checkout checkout) throws ServiceException {		
		checkout.setNightsCount(FormatDate.countDays(checkout.getCheckin(), checkout.getCheckout()));
		String message = costVerification(checkout);
		
		if( message == "ok" ) {
			Cost cost = new Cost();
			Listing listing = this.get(id);
			List<Double> prices = priceAndDiscount(listing, checkout);
			cost.setNights_count(checkout.getNightsCount());
			cost.setNights_cost(prices.get(0));
			cost.setDiscount(prices.get(1));
			cost.setCleaning_fee(prices.get(2));
			cost.setTotal(prices.get(3));
			return cost;	
		}else {
			throw new ServiceException(402,message);			
		}
	}
	
	@Override
	public HashMap<String, String> saveConfirmation(String id, Confirmation confirmation) {
		confirmation.setListing(id);
		confirmationRepository.save(confirmation);
		HashMap<String,String> response = new HashMap<String,String>();
		response.put("message", Response.OK_CONFIRMATION);
		return response;
	}
	
	private String costVerification(Checkout checkout) throws ServiceException{
		
		long checkoutTime = checkout.getCheckout().getTime();
		long checkinTime = checkout.getCheckin().getTime();
		long dateNow = new Date().getTime();
		
		String response = checkoutTime > checkinTime ? (checkinTime > dateNow ? (checkout.getNightsCount() <= 28 ? "ok" 
				: Response.ERROR_DATE_EXTEND) : Response.ERROR_OLD_DATE ) : Response.ERROR_CHECKOUT;
		
		return response;
						
	}
	
	private List<Double> priceAndDiscount(Listing listing, Checkout checkout) {
		List<SpecialPrice> listSpecialPrice = new ArrayList<SpecialPrice>();
		listing.getSpecialPrice().forEach(specialPrice ->{
			if(FormatDate.formatDate(specialPrice.getDate(), FormatDate.YYYY_MM_DD)
					.equals(FormatDate.formatDate(checkout.getCheckin(), FormatDate.YYYY_MM_DD))){
				listSpecialPrice.add(specialPrice);
			}
		});
		Double price = listSpecialPrice.isEmpty() ? listing.getBasePrice() : listSpecialPrice.get(0).getPrice();
		
		int nightsCount = checkout.getNightsCount();
		int week = nightsCount/7;
		Double priceWeek = listing.getWeeklyDiscount();
		Double priceMonthly = listing.getMonthlyDiscount();
		
		Double discountWeek = (nightsCount > 7 && week < 4) ?  nightsCount * priceWeek : 0;
		Double discountMonthly = (nightsCount >= 28) ? nightsCount * priceMonthly : 0;
		Double cleaningFee = listing.getCleaningFee() * nightsCount;
		Double total = ((price * nightsCount) + cleaningFee) - (discountWeek + discountMonthly);
		
		List<Double> priceAndDiscount = new ArrayList<>();
		priceAndDiscount.add(Math.floor(price*100)/100);
		priceAndDiscount.add(Math.floor(((discountWeek + discountMonthly) + ((listing.getBasePrice() * nightsCount) - (price * nightsCount))) * 100)/100);
		priceAndDiscount.add(Math.floor(cleaningFee*100)/100);
		priceAndDiscount.add(Math.floor(total*100)/100);

		return priceAndDiscount;
	}


	
}
