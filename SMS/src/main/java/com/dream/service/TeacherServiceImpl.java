package com.dream.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dream.model.Teacher;
import com.dream.repository.TeacherRepository;

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
	public Teacher getTeacherById(int user_id) {
		Teacher teacher = teacherRepo.findByUserId(user_id);
		return teacher;
	}

}
