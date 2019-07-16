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
	
	public boolean insertTeacher(User user);

	public Teacher getTeacherById(int user_id);
	
	public List<Teacher> getAllTeachers();
	
	public List<Teacher> get();
}
