package com.dream.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.dream.model.Student;
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
	public boolean insertStudent(Student student) {
		studentRepo.save(student);
		return false;
	}

	@Override
	public Student getStudentById(int id) {
		Student student = studentRepo.findById(id);
		return student;
	}

	@Override
	public List<Student> getAllStudents() {
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
	public Page<Student> getPaginated(Pageable pageable) {
		return studentRepo.findAll(pageable);
	}

	public List<Student> getParents() {
		return studentRepo.findAllParents();
	}

}
