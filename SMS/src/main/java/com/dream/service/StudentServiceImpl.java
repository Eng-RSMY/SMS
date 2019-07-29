package com.dream.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
	public List<Student> get(String st) {
		List<Student> list = new ArrayList<>();
		studentRepo.findAll().forEach(l -> list.add(l));
		List<Student> lNew = new ArrayList<>();
		list.forEach(l -> lNew.add(l));
		lNew.forEach(System.out::println);
		return lNew.stream().filter(s -> s.getUser().getName().toLowerCase().contains(st.toLowerCase())).collect(Collectors.toList());
	}

	@Override
	public Page<Student> getPaginated(Pageable pageable) {
		return studentRepo.findAll(pageable);
	}

	@Override
	public Student findByUser(User user) {
		return studentRepo.findByUser(user);
	}

	/*
	 * public List<Student> getParents() { return studentRepo.findAllParents(); }
	 */
}
