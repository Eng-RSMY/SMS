package com.dream.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.dream.model.Student;
/**
 * 
 * @author dileep
 *
 *	StudentRepository Created by Dileep on 17/07/2019
 */
public interface StudentRepository extends PagingAndSortingRepository<Student, Integer>{

	@Query(value = "select u.name,u.last_name,t.phone,t.subject,t.experience from user u JOIN student t ON u.user_id=t.user_id", nativeQuery = true)
	List<Student> findAllStudents();
	
	@Query(value = "select u.name,u.last_name,t.phone,t.subject,t.experience from user u JOIN student t ON u.user_id=t.user_id and where user_id=:id", nativeQuery = true)
	Student findByUserOn(@Param("id") int id);
	
	Student findById(int id);
}
