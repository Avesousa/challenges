package com.mavha.challenge.backend.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="confirmations")
public class Confirmation {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;
	
	@Column(name="id_listings")
	private String listing;

	@Column(name="checkin")
	private String checkin;
	
	@Column(name="checkout")
	private String checkout;
	
	@Column(name="adults")
	private int adults;
	
	@Column(name="children")
	private int children;
	
	@Column(name="pets")
	private boolean pets;

	@Column(name="message")
	private String message;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCheckin() {
		return checkin;
	}

	public void setCheckin(String checkin) {
		this.checkin = checkin;
	}

	public String getCheckout() {
		return checkout;
	}

	public void setCheckout(String checkout) {
		this.checkout = checkout;
	}

	public int getAdults() {
		return adults;
	}

	public void setAdults(int adults) {
		this.adults = adults;
	}

	public int getChildren() {
		return children;
	}

	public void setChildren(int children) {
		this.children = children;
	}

	public boolean isPets() {
		return pets;
	}

	public void setPets(boolean pets) {
		this.pets = pets;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getListing() {
		return listing;
	}

	public void setListing(String listing) {
		this.listing = listing;
	}
	
	
	
}
