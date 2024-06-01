package com.shopping.service;

import org.apache.commons.codec.binary.Base64;

public class test {

	public static void main(String[] args) {
		
		String str = "Hello worlds !!";
		Base64 base64 = new Base64();
		String encodedStr = new String(base64.encode(str.getBytes()));
		System.out.println(encodedStr); 
		
		String strdecode = "cHJpeWFkaWtvbmRhd2FyKzEyN0BnbWFpbC5jb20=";
		
		String decodedStr = new String(base64.decode(strdecode.getBytes()));
		
		System.out.println(decodedStr); //Hello worlds !!

	}

}
