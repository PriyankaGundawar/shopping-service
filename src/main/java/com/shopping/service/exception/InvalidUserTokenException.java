package com.shopping.service.exception;

@SuppressWarnings("serial")
public class InvalidUserTokenException extends Exception{
	public InvalidUserTokenException(String str){
		super(str);
	}

}
