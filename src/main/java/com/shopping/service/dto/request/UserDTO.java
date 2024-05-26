package com.shopping.service.dto.request;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.shopping.service.model.User;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
public class UserDTO {
	private int id;
	private String firstName;
	private String middleName;
	private String lastName;
	private String mobile;
	private String email;
//	@JsonProperty(access = "")
//	@JsonIgnore
//	@Column(name = "passwordHash")
	private String passwordHash;
	private int admin;
	private int vendor;
	private Date registeredAt;
	private Date lastLogin;
	private int intro;
	private String profile;
	private String token;
	private int status;
	private Date tokenCreatedAt;
	private Date lastPasswordUpdatedAt;
	
	public UserDTO() {
		super();
	}
	
	
	
//	public UserDTO getUserDTO(User user) {
//		UserDTO userDTO = new UserDTO();
//		userDTO.setId(user.getId());
//		userDTO.setFirstName(user.getFirstName());
//		userDTO.setMiddleName(user.getMiddleName());
//		userDTO.setLastName(user.getLastName());
//		userDTO.setMobile(user.getMobile());
//		userDTO.setEmail(user.getEmail());
//		userDTO.setPasswordHash(user.getPasswordHash());
//		userDTO.setAdmin(user.getAdmin());
//		userDTO.setVendor(user.getVendor());
//		userDTO.setRegisteredAt(user.getRegisteredAt());
//		userDTO.setLastLogin(user.getLastLogin());
//		userDTO.setIntro(user.getIntro());
//		userDTO.setProfile(user.getProfile());
//		userDTO.setToken(user.getToken());
//		userDTO.setStatus(user.getStatus());
//		userDTO.setTokenCreatedAt(user.getTokenCreatedAt());
//		userDTO.setLastPasswordUpdatedAt(user.getLastPasswordUpdatedAt());
//		
//		return userDTO;
//	}


}


