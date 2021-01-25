package com.mavha.challenge.backend.model;

import java.util.Date;

public class Checkout {
	
	private long checkin;
	private long checkout;
	private int nightsCount;
	
	public int getNightsCount() {
		return nightsCount;
	}
	public void setNightsCount(int nightsCount) {
		this.nightsCount = nightsCount;
	}
	public Date getCheckin() {
		return new Date(checkin);
	}
	public void setCheckin(Long checkin) {
		this.checkin = checkin;
	}
	public Date getCheckout() {
		return new Date(checkout);
	}
	public void setCheckout(Long checkout) {
		this.checkout = checkout;
	}
	@Override
	public String toString() {
		return "Checkout [checkin=" + checkin + ", checkout=" + checkout + ", nightsCount=" + nightsCount + "]";
	}
	
	
	
	

}
