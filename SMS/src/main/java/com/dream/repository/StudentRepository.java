package com.dream.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dream.model.Student;

public interface StudentRepository extends JpaRepository<Student, Integer>{

	
	
}
