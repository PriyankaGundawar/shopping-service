package com.shopping.service.util;

public class ApplicationConstants {
	
	public enum PaymentStatus {
		Completed, 
		Success, 
		Created, 
		Denied, 
		Expired, 
		Failed, 
		Pending, 
		Refunded, 
		Reversed, 
		Processed, 
		Voided,
		PartiallyPaid;
	}
	
	public enum PaymentType {
		Credit,
		Debit
	}
	
	public enum PaymentMode {
		Offline,
		CashOnDelivery,
		Cheque,
		Draft,
		Wired,
		Online
	}
	
	public enum OrderStatus {
		OrderCompleted, 
		OrderCancelled, 
		OrderDelivered, 
		OrderInTransit, 
		OrderPaymentDue, 
		OrderPartialPaymentDue,
		OrderPickupAvailable, 
		OrderPlaced,
		OredrProblem, 
		OrderProcessing, 
		OrderReturned;
	}
	
	public enum UserStatus {
		InActive,
		Active,
		PendingActive;
		
	}
	
	public enum TokenType {
		ForgotPassword,
		ForgotUserId,
		UserActivation;
	}
	
	

}
