package com.shopping.service.util;

import org.apache.commons.codec.binary.Base64;

public class Base64EncodeDecode {
	
	public String encode(String input) {
		Base64 base64 = new Base64();
		String encodedStr = new String(base64.encode(input.getBytes()));
		return encodedStr;
	}
	
	public String decode(String input) {
		Base64 base64 = new Base64();
		String decodedStr = new String(base64.decode(input.getBytes()));
		return decodedStr;
		
	}
	

	


}
