package com.shopping.service.controller;

import org.apache.commons.codec.binary.Base64;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shopping.service.exception.InvalidUserOrPasswordEntered;
import com.shopping.service.services.UserService;


//@RestController
//@RequestMapping("/user")
//public class UserLoginController {
//	
//	UserService userService;
//	
//	@GetMapping("/login")
//	public String userLogin(@RequestParam("email") String email, @RequestParam("password") String password) {
//		try {
//			userService.userLogin(email, password);
//			
//			String returnMessage = "login successfull";
//			
//			Base64 base64 = new Base64();
//			String encodedStr = new String(base64.encode(returnMessage.getBytes()));
//			
//			return encodedStr;
//		} catch (InvalidUserOrPasswordEntered e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			return e.getMessage();
//		}
//		
//	}
//
//}
