package com.dream.service;

import java.util.List;

import com.dream.model.User;

/**
 * User Service Interface.
 * @author dileep
 *
 */
public interface UserService {

	public User save(User user);
	
	public Boolean delete(int id);
	
	public User update(User user);
	
	public User findById(int id);
	
	public User findByName(String username);
	
	public User findByEmail(String email);
	
	public List<User> findAll();
	
}
