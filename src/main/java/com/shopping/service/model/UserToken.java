package com.shopping.service.model;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name = "user_token")
public class UserToken {
	
	@Id
	private int id;
//	private int userid;
	private String token;
	private Date createdAt;
	private String type;
	
	@ManyToOne
	@JoinColumn(name="userid",referencedColumnName = "id")
	private User user;

}
