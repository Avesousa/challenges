package com.mavha.challenge.backend.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name="listings")
public class Listing {
	
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;

	@JsonProperty("owner_id")
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "owner_id", nullable = false)
	private User user;

	@JsonProperty("special_prices")
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy="listing")
	private List<SpecialPrice> specialPrice;
	
	@Column(name="name", nullable = false)
	private String name;
	
	@Column(name="slug")
	private String slug;
	
	@Column(name="description", nullable = false)
	private String description;
	
	@Column(name="adults")
	private int adults;
	
	@Column(name="children")
	private int children;
	
	@JsonProperty("is_pets_allowed")
	@Column(name="is_pets_allowed")
	private boolean isPetAllowed;
	
	@JsonProperty("base_price")
	@Column(name="base_price", nullable = false)
	private Double basePrice;
	
	@JsonProperty("cleaning_fee")
	@Column(name="cleaning_fee")
	private Double cleaningFee;
	
	@JsonProperty("image_url")
	@Column(name="image_url")
	private String imageUrl;
	
	@JsonProperty("weekly_discount")
	@Column(name="weekly_discount")
	private Double weeklyDiscount;

	@JsonProperty("monthly_discount")
	@Column(name="monthly_discount")
	private Double monthlyDiscount;
	
	@Column(name="valoration")
	private int valoration;

	public String getId() {
		return id;
	}
	
	public boolean isUserNull() {
		return this.user == null;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUser() {
		return this.user.getId();
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSlug() {
		return slug;
	}

	public void setSlug(String slug) {
		this.slug = slug;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public boolean isPetAllowed() {
		return isPetAllowed;
	}

	public void setPetAllowed(boolean isPetAllowed) {
		this.isPetAllowed = isPetAllowed;
	}

	public Double getBasePrice() {
		return basePrice;
	}

	public void setBasePrice(Double basePrice) {
		this.basePrice = basePrice;
	}

	public Double getCleaningFee() {
		return cleaningFee;
	}

	public void setCleaningFee(Double cleaningFee) {
		this.cleaningFee = cleaningFee;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public Double getWeeklyDiscount() {
		return weeklyDiscount;
	}

	public void setWeeklyDiscount(Double weeklyDiscount) {
		this.weeklyDiscount = weeklyDiscount;
	}

	public Double getMonthlyDiscount() {
		return monthlyDiscount;
	}

	public void setMonthlyDiscount(Double monthlyDiscount) {
		this.monthlyDiscount = monthlyDiscount;
	}

	public List<SpecialPrice> getSpecialPrice() {
		return specialPrice;
	}

	public void setSpecialPrice(List<SpecialPrice> specialPrice) {
		this.specialPrice = specialPrice;
	}
	
	

	public int getValoration() {
		return valoration;
	}

	public void setValoration(int valoration) {
		this.valoration = valoration;
	}	
	
	
		
}
