package com.shopping.service.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;



// Note: We cannot revert the actual string once it has been hashed.
// Note in case of encoding : we can retrieve back the actual string after encoding(we can do decoding). 

public class HashingUtil {
	
	public String stringHashing(String stringToHash) {
		
		MessageDigest digest = null;
		try {
			digest = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		byte[] stringAfterHashing = digest.digest(stringToHash.getBytes(StandardCharsets.UTF_8));
		String hashedString = new String(stringAfterHashing, StandardCharsets.UTF_8);
		return hashedString;
		
	}
	

}


