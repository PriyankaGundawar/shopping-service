package com.shopping.service.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopping.service.dao.UserDAO;
import com.shopping.service.exception.BadRequestException;
import com.shopping.service.model.EmailParams;
import com.shopping.service.model.User;
import com.shopping.service.util.ApplicationConstants.UserStatus;
import com.shopping.service.util.GenerateRandomString;
import com.shopping.service.util.HashingUtil;
import com.shopping.service.util.SendEmail;
import com.shopping.service.validation.UserValidation;

@Service
public class UserService {

	@Autowired
	UserDAO userDao;

	HashingUtil hashUtil = new HashingUtil();

	public List<User> findAll() {
		return userDao.findAll();
	}

	public Optional<User> findById(int id) {
		return userDao.findById(id);
	}

	public User save(User user) throws BadRequestException {
		user.setLastLogin(new Date(System.currentTimeMillis()));
		user.setRegisteredAt(new Date(System.currentTimeMillis()));
		user.setStatus(UserStatus.PendingActive.ordinal());
		user.setLastPasswordUpdatedAt(new Date(System.currentTimeMillis()));

		UserValidation validation = new UserValidation();
		try {
			if (validation.validate(user)) {
				// create activation token method and call here.

				GenerateRandomString randomToken = new GenerateRandomString();
				String token = randomToken.getAlphaNumericString(15);
				user.setToken(token);
				user.setTokenCreatedAt(new Date(System.currentTimeMillis()));

				String pass = hashUtil.stringHashing(user.getPasswordHash());
				user.setPasswordHash(pass);

				userDao.save(user);

				EmailParams emailParams = new EmailParams();
				String name = user.getFirstName() + " " + user.getLastName();
				emailParams.setEmailTo(user.getEmail());
				emailParams.setSubject("Registered successfully");
				emailParams.setEmailBody("Hello " + name + ", " + "you have been registered successfully.");

				SendEmail sendEmail = new SendEmail();
				sendEmail.sendEmail(emailParams);

				EmailParams userActivationemailParams = new EmailParams();
				String userName = user.getFirstName() + " " + user.getLastName();
//				String token = user.getToken();
//				URL url = new URL(token);
				userActivationemailParams.setEmailTo(user.getEmail());
				userActivationemailParams.setSubject("user account Activation");
				userActivationemailParams
						.setEmailBody("Hello " + userName + ", " + "Please activate your account." + token);

				SendEmail userActivationSendEmail = new SendEmail();
				userActivationSendEmail.sendEmail(userActivationemailParams);

				System.out.println("User Added :: " + user); // checl log for levels

			}

			return user;
		} catch (BadRequestException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			// add logger here with error level
			throw e;
		}

	}

	public String deleteById(int id) {
		userDao.deleteById(id);
		return "deleted";
	}

}
