package com.dream.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.dream.model.Student;
/**
 * 
 * @author dileep
 *
 *	StudentService Interface Created by Dileep on 17/07/2019
 */
public interface StudentService {

	public boolean insertStudent(Student student);

	public Student getStudentById(int id);
	
	public List<Student> getAllStudents();
	
	public List<Student> get();
	
	public Page<Student> getPaginated(Pageable pageable);
}
