package com.shopping.service.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name="User")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
//	@Column(name = "firstname")
	private String firstName;
	private String middleName;
	private String lastName;
	private String mobile;
	private String email;
	private String passwordHash;
	private int admin;
	private int vendor;
	private Date registeredAt;
//	@Column(name="lastlogin")
	private Date lastLogin;
	private int intro;
	private String profile;
	private String token;
	private int status;
//	@Column(name = "token_createdAt")
	private Date tokenCreatedAt;
//	@Column(name = "last_password_updatedAt")
	private Date lastPasswordUpdatedAt;
	

}
