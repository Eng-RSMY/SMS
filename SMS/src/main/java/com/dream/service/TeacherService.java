package com.dream.service;

import java.util.List;

import com.dream.model.Teacher;
import com.dream.model.User;
/**
 * 
 * @author dileep
 * 
 * TeacherService created by Dileep on 15/07/2019
 *
 */
public interface TeacherService {
	
	public boolean insertTeacher(Teacher teacher);

	public Teacher getTeacherById(int id);
	
	public Teacher getTeacherByUserId(User id);
	
	public List<Teacher> getAllTeachers();
	
	public List<Teacher> get();
}
