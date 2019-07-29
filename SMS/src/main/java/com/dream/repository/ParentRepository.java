package com.dream.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.dream.model.Parent;
import com.dream.model.User;

public interface ParentRepository extends PagingAndSortingRepository<Parent, Integer>{

	Parent findById(int id);
	
	Parent findByUser(User user);
	
}
