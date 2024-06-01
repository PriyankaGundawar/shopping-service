package com.shopping.service.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import com.shopping.service.model.User;
import com.shopping.service.model.UserToken;
import com.shopping.service.respository.UserTokenRepository;
import com.shopping.service.util.ApplicationConstants.TokenType;
import com.shopping.service.util.GenerateRandomString;

public class UserTokenService {
	
	@Autowired
	UserTokenRepository userTokenRepository;
	
//	public UserToken saveUserToken(UserToken userToken) {
//		userTokenRepository.save(userToken);
//		
//		return userToken;
//	}
	
	public UserToken forgotPasswordToken(User user) {
		UserToken userToken = new UserToken();
		GenerateRandomString generateRandomString = new GenerateRandomString();
		String token = generateRandomString.getAlphaNumericString(20);
		
		userToken.setUser(user);
		userToken.setToken(token);
		userToken.setType(TokenType.ForgotPassword.toString());
		userToken.setCreatedAt(new Date(System.currentTimeMillis()));
		userTokenRepository.save(userToken);		
		return userToken;
	}

}
