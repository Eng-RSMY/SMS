package com.dream.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dream.model.Teacher;
import com.dream.service.TeacherServiceImpl;

/**
 * 
 * @author dileep
 * TeacherController created by Dileep on 15/07/2019
 */
@Controller
public class TeacherController {
	
	@Autowired
	private TeacherServiceImpl teacherService;
	
	@RequestMapping(value = "/teacherDetails/{id}", method = RequestMethod.GET)
	public String getTeacherDetails(@PathVariable("id") int id, Model model) {
		Teacher teacher = teacherService.getTeacherById(id);
		model.addAttribute("teacher", teacher);
		return "";
	}
	
	@RequestMapping(value = "jsonallTeacherslist", method = RequestMethod.GET)
	public String getAllTeachers(Model model){
		List<Teacher> list = teacherService.get();
		model.addAttribute("teachers", list);
		return "allteachers";
	}
}
