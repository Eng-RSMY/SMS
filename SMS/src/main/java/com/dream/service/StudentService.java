package com.dream.service;

import java.util.List;

import com.dream.model.Student;
import com.dream.model.User;
/**
 * 
 * @author dileep
 *
 *	StudentService Interface Created by Dileep on 17/07/2019
 */
public interface StudentService {

	public boolean insertStudent(User user);

	public Student getStudentById(int user_id);
	
	public List<Student> getAllTeachers();
	
	public List<Student> get();
	
	public List<Student> getPaginated();
}
