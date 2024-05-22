package com.shopping.service.util;

import java.time.Duration;
import java.time.Instant;
import java.util.Date;

import org.joda.time.DateTime;
import org.joda.time.DateTimeUtils;

public class TokenTimeElapsedValidation {
	
	public static void main(String[] args) {
		
		Date twentyMinutes = new DateTime().minusMinutes(35).toDate();
		Date currentTime = new DateTime().toDate();
		
		System.out.println(twentyMinutes);
		System.out.println(currentTime);
		
		long diff = twentyMinutes.getTime() - currentTime.getTime(); //return in milli seconds
		System.out.println(diff/60000);
		
		
		
//		new Date(System.currentTimeMillis());
		
//		if(System.currentTimeMillis() - sysMinusTwoDays == 20) {
//			System.out.println("In time");
//		}
//		else {
//			System.out.println("not in time");
//		}
	}


//public boolean tokenValidationTime(Date tokenCreatedAt, Date currentTime) {
//	
//	DateTime twentyMinutes = new DateTime().minusMinutes(20);
//	tokenCreatedAt = twentyMinutes.toDate();
//	tokenCreatedAt.setHours(0);
//	tokenCreatedAt.setMinutes(20);
//	tokenCreatedAt.setSeconds(0);
//	
//	System.out.println(tokenCreatedAt);
//	
//	currentTime = new Date(System.currentTimeMillis());
//	
//	if(currentTime.setMinutes(20) == tokenCreatedAt) {
//		
//	}
//	return false;
//	
//}
}