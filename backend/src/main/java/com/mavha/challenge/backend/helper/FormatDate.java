package com.mavha.challenge.backend.helper;

import java.text.SimpleDateFormat;
import java.util.Date;

public class FormatDate {
	
	public static String YYYY_MM_DD = "yyyy-MM-dd";
	
	public static String formatDate(Date date, String format){
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
		return simpleDateFormat.format(date);
	}
	
	public static int countDays(Date dateIn, Date dateOut) {
		return (int) ((dateOut.getTime() - dateIn.getTime()) / 86400000);
	}

}
