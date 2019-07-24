package com.dream.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.dream.model.Teacher;

/**
 * 
 * @author dileep
 * 
 * TeacherRepository model Created by Dileep on 15/07/2019
 *
 */
public interface TeacherRepository extends CrudRepository<Teacher,Integer>{

	@Query(value = "select u.name,u.last_name,t.phone,t.subject,t.experience from user u JOIN teacher t ON u.user_id=t.user_id", nativeQuery = true)
	List<Teacher> findAllTeachers();
	
	@Query(value = "select u.name,u.last_name,t.phone,t.subject,t.experience from user u JOIN teacher t ON u.user_id=t.user_id and where user_id=:id", nativeQuery = true)
	Teacher findByUserOn(@Param("id") int id);
	
	Teacher findById(int id);
}
