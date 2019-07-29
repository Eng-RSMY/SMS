package com.dream.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.dream.model.Parent;
import com.dream.model.User;
/**
 * 
 * @author dileep
 *
 *	ParentService Interface Created by Dileep on 25/07/2019
 */
public interface ParentService {

	public boolean save(Parent student);

	public Parent getParentById(int id);
	
	public Parent findByUser(User user);
	
	public List<Parent> get();
	
	public Page<Parent> getPaginated(Pageable pageable);
}
