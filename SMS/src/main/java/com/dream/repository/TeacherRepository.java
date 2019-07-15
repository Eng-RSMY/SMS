package com.dream.repository;

import org.springframework.data.repository.CrudRepository;

import com.dream.model.Teacher;

/**
 * 
 * @author dileep
 * 
 * TeacherRepository model Created by Dileep on 15/07/2019
 *
 */
public interface TeacherRepository extends CrudRepository<Teacher,Integer>{

	Teacher findByUserId(int userId);
	
}
