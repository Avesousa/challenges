package com.mavha.challenge.backend.helper;

public abstract class Response {

	public static String ERROR = "An error has occurred";
	public static String ERROR_CHECKOUT = "Checkout date cannot be before checkin";
	public static String ERROR_OLD_DATE = "You have tried to find out the costs of past dates";
	public static String ERROR_DATE_EXTEND = "It is only allowed to book for 28 days";
	public static String NOT_VALUE = "No values ​​found to display";
	public static String QUERY_ERROR = "An error was found in the query";
	public static String DELETE_NOT_EXITS = "What you are trying to delete does not exist in our database";
	public static String VALUE_NOT_NULL = "It seems that there is a lack of data to add";
	public static String LOGIN_NOT_FOUND = "An error occurred while logging in";
	public static String TOKEN_NOT_VALIDATE = "An error occurred with authorization";
	public static String OK_CONFIRMATION = "success! thanks for your reservation";
}
