package com.shopping.service.controller;

import java.util.List;
import java.util.Optional;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.shopping.service.dto.ErrorResponse;
import com.shopping.service.dto.LoginResponse;
import com.shopping.service.dto.SuccessResponse;
import com.shopping.service.dto.request.UserDTO;
import com.shopping.service.exception.BadRequestException;
import com.shopping.service.exception.InvalidUserOrPasswordEntered;
import com.shopping.service.exception.InvalidUserTokenException;
import com.shopping.service.exception.UserNotFoundException;
import com.shopping.service.model.User;
import com.shopping.service.respository.UserRepository;
import com.shopping.service.services.UserService;
import com.shopping.service.util.ApplicationConstants.ResponseType;
import com.shopping.service.util.Base64EncodeDecode;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/user")
public class UserController {

	Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	UserRepository userDao;

	@Autowired
	UserService userService;

//	@RequestMapping("/")
//	public String home() {
//		return "home.jsp";
//	}

	@PostMapping(path = "/add")
	public ResponseEntity addUser(UserDTO userDTO) {
		try {
			UserDTO savedUser = userService.save(userDTO);
			return new ResponseEntity<>(savedUser, HttpStatus.CREATED);

		} catch (BadRequestException e) {
			e.printStackTrace();

			ErrorResponse errorResponse = new ErrorResponse();
			errorResponse.setType(ResponseType.Error.toString());
			errorResponse.setMessage(e.getMessage());
			errorResponse.setErrorCode("BAD_REQUEST");

//	        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
//	        return new ResponseEntity<>(errorResponse);
			return ResponseEntity.badRequest().body(errorResponse);
		}

	}

//	To do : write update method separately in user service.
	@PutMapping(path = "/update")
	public ResponseEntity updateUser(UserDTO userDTO) {
		UserDTO updatedUser;
		try {
			updatedUser = userService.updateUser(userDTO);
			return new ResponseEntity<>(updatedUser, HttpStatus.OK);
		} catch (BadRequestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ErrorResponse errorResponse = new ErrorResponse();
			errorResponse.setType(ResponseType.Error.toString());
			errorResponse.setMessage(e.getMessage());
			errorResponse.setErrorCode("BAD_REQUEST");
			return ResponseEntity.badRequest().body(errorResponse);
		}

	}

	@GetMapping("/list")
	public List<UserDTO> getUsers() {
		return userService.findAll();
	}

	@RequestMapping("/{id}")
	public ResponseEntity<UserDTO> getUser(@PathVariable("id") int id) {
		UserDTO userDTO = this.userService.findById(id);
		return new ResponseEntity<UserDTO>(userDTO, HttpStatus.OK);
//		return userService.findById(id);
	}

	@DeleteMapping("/{id}")
	public String deleteUsers(@PathVariable("id") int id) {
//		Optional<User> user = userDao.findById(id);
		userService.deleteById(id);

		return "deleted User";

	}

	@GetMapping("/search")
	public User getUserFirstNameAndMobile(@RequestParam("firstName") String firstName,
			@RequestParam("mobile") String mobile) {
		logger.debug("firstName: " + firstName + " mobile: " + mobile);
		return userService.findByFirstNameAndMobile(firstName, mobile);
	}

	@RequestMapping("/activationToken")
	public ResponseEntity userActivation(@RequestParam("token") String token)
			throws BadRequestException, InvalidUserTokenException {
		userService.userActivation(token);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping("/login")
	public ResponseEntity userLogin(@RequestParam("email") String email, @RequestParam("password") String password) {
		try {
			userService.userLogin(email, password);

			Base64EncodeDecode encoding = new Base64EncodeDecode();
			LoginResponse loginResponse = new LoginResponse();
			loginResponse.setAccessToken(encoding.encode(email));

			return new ResponseEntity<LoginResponse>(loginResponse, HttpStatus.OK);

		} catch (InvalidUserOrPasswordEntered e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.FORBIDDEN);
		}

	}

	@GetMapping("/password-change")
	public ResponseEntity userPasswordChange(String email, String oldPassword, String newPassword) {
		try {
			userService.userPasswordChange(email, oldPassword, newPassword);
			SuccessResponse successResponse = new SuccessResponse();
			successResponse.setSuccessMessage("Password has been changed !!");
			return new ResponseEntity<SuccessResponse>(successResponse, HttpStatus.OK);

		} catch (BadRequestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		} catch (InvalidUserOrPasswordEntered e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}

	}

	@GetMapping("/forgot-password")
	public ResponseEntity forgotPassword(String email) {
		try {
			userService.forgotPassword(email);
			return new ResponseEntity<String>("Email has been sent to reset the password", HttpStatus.OK);
		} catch (UserNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}

	}

}
