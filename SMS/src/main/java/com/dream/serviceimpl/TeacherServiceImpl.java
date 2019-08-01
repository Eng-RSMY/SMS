package com.dream.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dream.model.Teacher;
import com.dream.repository.TeacherRepository;
import com.dream.service.TeacherService;

/**
 * 
 * @author dileep
 *
 *	TeacherService created by Dileep on 15/07/2019
 */
@Service
public class TeacherServiceImpl implements TeacherService{

	@Autowired
	private TeacherRepository teacherRepo;
	
	@Override
	public Teacher getTeacherById(int id) {
		Teacher teacher = teacherRepo.findById(id);
		return teacher;
	}

	@Override
	public List<Teacher> getAllTeachers() {
		List<Teacher> list = teacherRepo.findAllTeachers();
		return list;
	}

	@Override
	public List<Teacher> get(){
		List<Teacher> list = new ArrayList<>();
		teacherRepo.findAll().forEach(l -> list.add(l));
		return list;
	}

	@Override
	public boolean insertTeacher(Teacher teacher) {
		teacherRepo.save(teacher);
		return true;
	}
	
}
