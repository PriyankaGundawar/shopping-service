package com.shopping.service.controller;

import java.util.List;
import java.util.Optional;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.shopping.service.dao.UserDAO;
import com.shopping.service.exception.BadRequestException;
import com.shopping.service.model.User;
import com.shopping.service.services.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@RestController
public class UserController {

	@Autowired
	UserDAO userDao;

	@Autowired
	UserService userService;

//	@RequestMapping("/")
//	public String home() {
//		return "home.jsp";
//	}

	@PostMapping(path = "/addUser")
	public ResponseEntity addUser(User user) {
		try {
			userService.save(user);
			return new ResponseEntity<>(user, HttpStatus.CREATED);

		} catch (BadRequestException e) {
			e.printStackTrace();
	        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}

	}

	@PutMapping(path = "/saveOrUpdateUser")
	public User saveOrUpdateUser(User user) throws BadRequestException {
		userService.save(user);
		return user;

	}

	@GetMapping("/getUsers")
	public List<User> getUsers() {
		return userService.findAll();
	}

	@RequestMapping("/getUser/{id}")
	public Optional<User> getUser(@PathVariable("id") int id) {
		return userService.findById(id);
	}

	@DeleteMapping("/deleteUser/{id}")
	public String deleteUsers(@PathVariable("id") int id) {
//		Optional<User> user = userDao.findById(id);
		userService.deleteById(id);

		return "deleted User";

	}

}
