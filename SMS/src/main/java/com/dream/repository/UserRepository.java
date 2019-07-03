package com.dream.repository;

import org.springframework.data.repository.CrudRepository;

import com.dream.model.User;
/**
 * 
 * @author Dileep
 * 
 * UserRepository created by Dileep on 16/05/2019
 *
 */
public interface UserRepository extends CrudRepository<User,Integer>{

	//It creates select query based on User name.
	User findByName(String name);

	//It creates select query based on User email.
	User findByEmail(String email);
	
}
