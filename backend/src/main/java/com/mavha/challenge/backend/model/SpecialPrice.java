package com.mavha.challenge.backend.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="special_prices")
public class SpecialPrice {
	
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "listing_id", nullable = false)
	private Listing listing;
	
	@Column(name="date")
	private Date date;
	
	@Column(name="price")
	private Double price;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getListing() {
		return listing.getId();
	}

	public void setListing(Listing listing) {
		this.listing = listing;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
	
	
	
}
