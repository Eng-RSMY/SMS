package com.dream.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dream.model.Student;
import com.dream.model.User;
import com.dream.repository.StudentRepository;
/**
 * 
 * @author dileep
 *
 *	StudentServiceImpl Created by Dileep on 17/07/2019
 */
@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentRepository studentRepo;
	
	@Override
	public boolean insertStudent(User user) {
		studentRepo.save(new Student(user));
		return false;
	}

	@Override
	public Student getStudentById(int user_id) {
		Student student = studentRepo.findByUserOn(user_id);
		return student;
	}

	@Override
	public List<Student> getAllTeachers() {
		List<Student> list = studentRepo.findAllStudents();
		return list;
	}

	@Override
	public List<Student> get() {
		List<Student> list = new ArrayList<>();
		studentRepo.findAll().forEach(l -> list.add(l));
		return list;
	}

	@Override
	public List<Student> getPaginated() {
		// TODO Auto-generated method stub
		return null;
	}

}
