package com.dream.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.dream.model.Student;
import com.dream.model.User;
/**
 * 
 * @author dileep
 *
 *	StudentService Interface Created by Dileep on 17/07/2019
 */
public interface StudentService {

	public boolean insertStudent(Student student);

	public Student getStudentById(int id);
	
	public List<Student> getAllStudents(int classOfStudy);
	
	public List<Student> get(String st);
	
	public Page<Student> getPaginated(Pageable pageable);
	
	public Student findByUser(User user);

	public int getCount();
}
