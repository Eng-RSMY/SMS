package com.dream.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
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
	
	//It selects User bases on firstName and LastName
	User findByNameAndLastName(String name, String lastName);
	
	@Query(value = "select * from user where role=5", nativeQuery = true)
	List<User> findAllUsers();
}
