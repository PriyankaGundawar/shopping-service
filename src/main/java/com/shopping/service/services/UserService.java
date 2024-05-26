package com.shopping.service.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopping.service.dto.EmailParams;
import com.shopping.service.dto.request.UserDTO;
import com.shopping.service.exception.BadRequestException;
import com.shopping.service.exception.InvalidUserOrPasswordEntered;
import com.shopping.service.exception.InvalidUserTokenException;
import com.shopping.service.model.User;
import com.shopping.service.respository.UserRepository;
import com.shopping.service.util.ApplicationConstants.UserStatus;
import com.shopping.service.util.GenerateRandomString;
import com.shopping.service.util.HashingUtil;
import com.shopping.service.util.SendEmail;
import com.shopping.service.validation.UserValidation;

@Service
public class UserService {

	Logger logger = LoggerFactory.getLogger(UserService.class);

	@Autowired
	UserRepository userRepository;

	HashingUtil hashUtil = new HashingUtil();

	@Autowired
	private ModelMapper modelMapper;

	public List<UserDTO> findAll() {
		List<User> users = userRepository.findAll();
		List<UserDTO> usersDTO = new ArrayList<UserDTO>();
		for (User u : users) {
			UserDTO userDTO = this.modelMapper.map(u, UserDTO.class);
			usersDTO.add(userDTO);
		}
		logger.info("List of users added to dto" + usersDTO);

		return usersDTO;
	}

	public UserDTO findById(int id) {
		Optional<User> user = userRepository.findById(id);
		logger.info("returning user" + user);
		UserDTO userDTO = this.modelMapper.map(user, UserDTO.class);
		logger.info("returning user DTO" + userDTO);
		return userDTO;
	}

	public UserDTO save(UserDTO userDTO) throws BadRequestException {

		UserValidation validation = new UserValidation();

		UserDTO savedUserDTO = null;
		try {
			if (validation.validate(userDTO)) {

				GenerateRandomString randomToken = new GenerateRandomString();
				String token = randomToken.getAlphaNumericString(15);
				userDTO.setToken(token);
				userDTO.setTokenCreatedAt(new Date(System.currentTimeMillis()));

				String pass = hashUtil.stringHashing(userDTO.getPasswordHash());
				userDTO.setPasswordHash(pass);

				User user = this.modelMapper.map(userDTO, User.class);

				user.setLastLogin(new Date(System.currentTimeMillis()));
				user.setRegisteredAt(new Date(System.currentTimeMillis()));
				user.setStatus(UserStatus.PendingActive.ordinal());
				user.setLastPasswordUpdatedAt(new Date(System.currentTimeMillis()));

				userRepository.save(user);

				savedUserDTO = this.modelMapper.map(user, UserDTO.class);

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

//				System.out.println("User Added :: " + user); // checl log for levels

				logger.debug("User Added :: " + user);
				;

			}

			return savedUserDTO;
		} catch (BadRequestException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			// add logger here with error level
			throw e;
		}

	}

	public UserDTO updateUser(UserDTO userDTO) throws BadRequestException {

		UserValidation validation = new UserValidation();
		UserDTO updatedUserDTO = null;
		try {
			if (validation.validate(userDTO)) {

				UserDTO userDTOFromId = findById(userDTO.getId());
				User user = this.modelMapper.map(userDTOFromId, User.class);
				user.setFirstName(userDTO.getFirstName());
				user.setLastName(userDTO.getLastName());
				user.setMiddleName(userDTO.getMiddleName());
				user.setMobile(userDTO.getMobile());
				user.setAdmin(userDTO.getAdmin());
				user.setVendor(userDTO.getAdmin());
				user.setIntro(userDTO.getIntro());
				user.setProfile(userDTO.getProfile());
				userRepository.save(user);

				updatedUserDTO = this.modelMapper.map(user, UserDTO.class);

				EmailParams emailParams = new EmailParams();
				String name = user.getFirstName() + " " + user.getLastName();
				emailParams.setEmailTo(user.getEmail());
				emailParams.setSubject("User Details update");
				emailParams.setEmailBody("Hello " + name + ", " + "your details have been updated successfully.");

				SendEmail sendEmail = new SendEmail();
				sendEmail.sendEmail(emailParams);

			}
		} catch (BadRequestException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			throw e;
		}

		return updatedUserDTO;
	}

	public String deleteById(int id) {
		userRepository.deleteById(id);
		return "deleted";
	}

	public User findByFirstNameAndMobile(String firstName, String mobile) {
		return userRepository.findByFirstNameAndMobile(firstName, mobile);
	}

	public User userActivation(String token) throws BadRequestException, InvalidUserTokenException {
		User user = userRepository.findByToken(token);
		if (user == null) {
			throw new InvalidUserTokenException("Invalid User token sent: ");
		}
		user.setToken(null);
		user.setStatus(UserStatus.Active.ordinal());
		logger.debug("before save user token and status" + user);
		userRepository.save(user);
		logger.debug("after save user token and status" + user);
		return user;
	}

	public void userLogin(String email, String password) throws InvalidUserOrPasswordEntered {
		User user = userRepository.findByEmail(email);

		if (user == null) {
			throw new InvalidUserOrPasswordEntered("Invalid User or Password entered !!");
		}

		String passwrodFromDB = user.getPasswordHash();
		String hashedPasswordFromUser = hashUtil.stringHashing(password);

		if (passwrodFromDB.equals(hashedPasswordFromUser)) {
			// hash entered password and compare
			System.out.println("Correct pwd !!");
		} else {
			throw new InvalidUserOrPasswordEntered("Invalid User or Password entered !!");
		}
	}

}
