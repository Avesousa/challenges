package com.mavha.challenge.backend.service;

import java.util.HashMap;

public class ServiceException extends Exception {
	
	
	private static final long serialVersionUID = 1L;
	private int httpStatus;
	
	public ServiceException(){
		super();
	}
	
	public ServiceException(int httpStatus,String message) {
		super(message);
		this.httpStatus = httpStatus;
	}

	public int getNumberError() {
		return this.httpStatus;
	}

	public void setError(int httpStatus) {
		this.httpStatus = httpStatus;
	}
	
	 public HashMap<String,Object> getResponseError(){
		 HashMap<String,Object> response = new HashMap<>();
		 response.put("error", this.httpStatus);
		 response.put("message", this.getMessage());
		 return response;
	 }
	
}
