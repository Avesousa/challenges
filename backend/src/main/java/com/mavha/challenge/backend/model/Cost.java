package com.mavha.challenge.backend.model;

public class Cost {
	
	private int nights_count;
	private Double nights_cost;
	private Double discount;
	private Double cleaning_fee;
	private Double total;
	
	public int getNights_count() {
		return nights_count;
	}
	public void setNights_count(int nights_count) {
		this.nights_count = nights_count;
	}
	public Double getNights_cost() {
		return nights_cost;
	}
	public void setNights_cost(Double nights_cost) {
		this.nights_cost = nights_cost;
	}
	public Double getDiscount() {
		return discount;
	}
	public void setDiscount(Double discount) {
		this.discount = discount;
	}
	public Double getCleaning_fee() {
		return cleaning_fee;
	}
	public void setCleaning_fee(Double cleaning_fee) {
		this.cleaning_fee = cleaning_fee;
	}
	public Double getTotal() {
		return total;
	}
	public void setTotal(Double total) {
		this.total = total;
	}
	
	
	

}
