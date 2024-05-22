package com.shopping.service.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.shopping.service.model.User;

@Repository
public interface UserDAO extends JpaRepository<User, Integer>{
//public interface UserDAO extends CrudRepository<User, Integer>{
	
//	public List<User> findByAddress(String address);
//	
//	@Query("from User where address = ?1 order by name")
//	public List<User> findByAddressSorted(String address);

}
