package com.shopping.service.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.shopping.service.dto.request.UserDTO;
import com.shopping.service.exception.BadRequestException;
import com.shopping.service.model.User;

public class UserValidation {

	public boolean validate(UserDTO userDTO) throws BadRequestException {
		String firstName = userDTO.getFirstName();
		String lastName = userDTO.getLastName();
		String mobile = userDTO.getMobile();
		String email = userDTO.getEmail();
		String password = userDTO.getPasswordHash();

//		String regexPatternemail = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@" //correct one used for first time
//				+ "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$"; //it is accepting dot in front. correct it later
		
//		String regexPatternemail = "^(?=.{1,64}@)[A-Za-z0-9_-]+(?:\\.[A-Za-z0-9_-]+)*@"
//		        + "[^-][A-Za-z0-9-]+(?:\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
		
//		String regexPatternemail = "^(?=.{1,64}@)[A-Za-z0-9][A-Za-z0-9_-]*(?:\\.[A-Za-z0-9_-]+)*@"
//		        + "[^-][A-Za-z0-9-]+(?:\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
		
		String regexPatternemail = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9+_-]+)*@"
		+ "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
		
//		Pattern pemail = Pattern.compile(regexPatternemail);
//		Matcher memail = pemail.matcher(email);

		String firstNamePattern = "^[a-zA-Z][0-9a-zA-Z .,'-]*$";
		Pattern pfname = Pattern.compile(firstNamePattern);
		Matcher mfname = pfname.matcher(firstName);

		Pattern plname = Pattern.compile(firstNamePattern);
		Matcher mlname = plname.matcher(lastName);

		String mobilepattern = "^(\\+?\\d{1,3})?[- \\.]?\\d{3}[- \\.]?\\d{3}[- \\.]?\\d{4}$";
		Pattern pmobile = Pattern.compile(mobilepattern);
		Matcher mmobile = pmobile.matcher(mobile);

		String pwdpattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*()])[a-zA-Z0-9!@#$%^&*()]{8,}$";
		Pattern ppwd = Pattern.compile(pwdpattern);
		Matcher mpwd = ppwd.matcher(password);

		if (firstName == null || firstName.length() < 5 || !mfname.find()) {
			throw new BadRequestException("first name cannot be empty and minimum 5 letters");
		} else if (lastName == null || !mlname.find()) {
			throw new BadRequestException("last name cannot be empty");
		} else if (mobile == null || mobile.length() < 10 || !mmobile.find()) {
			throw new BadRequestException("Enter valid mobile number");
//		} else if (email == null || !memail.find()) {
//			throw new BadRequestException("Email cannot be null or Enter valid email");
		} else if (password == null || password.length() < 5 || !mpwd.find()) {
			throw new BadRequestException("Please enter valid password");
		}
		return true;
	}

}
