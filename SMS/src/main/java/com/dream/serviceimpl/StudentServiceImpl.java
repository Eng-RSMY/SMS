package com.dream.serviceimpl;

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
import com.dream.service.StudentService;
/**
 * 
 * @author dileep
 *
 *	StudentServiceImpl Created by Dileep on 17/07/2019
 */
@Service
public class StudentServiceImpl implements StudentService {

	private static final Logger logger = LogManager.getLogger(ParentController.class);
	
	@Autowired
	private StudentRepository studentRepo;
	
	@Override
	public boolean insertStudent(Student student) {
		if(student != null{
			studentRepo.save(student);
			logger.info("Student saved Successfully..!");
			return true;
		}
		return false;
	}

	@Override
	public Student getStudentById(int id) {
		Student student = studentRepo.findById(id);
		return student;
	}

	@Override
	public List<Student> getAllStudents(int classOfStudy){
		List<Student> list = studentRepo.findByClassOfStudy(classOfStudy);
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
