package com.shopping.service.respository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shopping.service.model.User;
import com.shopping.service.model.UserToken;

public interface UserTokenRepository extends JpaRepository<UserToken, Integer>{
	
	public UserToken findByToken();

}
