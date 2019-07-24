package com.dream.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dream.model.Teacher;
import com.dream.model.User;
import com.dream.service.TeacherService;
import com.dream.service.UserService;

/**
 * 
 * @author dileep
 * TeacherController created by Dileep on 15/07/2019
 */
@Controller
public class TeacherController {
	
	@Autowired
	private TeacherService teacherService;
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/teacherDetails/{id}", method = RequestMethod.GET)
	public String getTeacherDetails(@PathVariable("id") int id, Model model) {
		Teacher teacher = teacherService.getTeacherById(id);
		model.addAttribute("teacher", teacher);
		return "editTeacher";
	}
	
	@RequestMapping(value = "/editTeacher", method = RequestMethod.POST)
	public String updateTeacher(@ModelAttribute Teacher reqTeacher, @ModelAttribute User reqUser) {
		if(reqUser != null && reqTeacher != null) {
			User actualUser = userService.findById(reqTeacher.getUser().getId());
			System.out.println(actualUser.toString());
			if(actualUser != null) {
				actualUser.setName(reqUser.getName())
							.setLastName(reqUser.getLastName())
							.setEmail(reqUser.getEmail());
				userService.save(actualUser);
				System.out.println("User data Updated..!");
			}
			Teacher actualTeacher = teacherService.getTeacherById(reqTeacher.getId());
			System.out.println(actualTeacher.toString());
			if(actualTeacher != null) {
				actualTeacher.setDob(reqTeacher.getDob())
								.setExperience(reqTeacher.getExperience())
								.setGender(reqTeacher.getGender())
								.setLanguage(reqTeacher.getLanguage())
								.setSubject(reqTeacher.getSubject())
								.setPhone(reqTeacher.getPhone());
				teacherService.insertTeacher(actualTeacher);
				System.out.println("Teacher data Updated..!");
				return "redirect:/allTeachers";
			}
		}
		return "redirect:/allTeachers";
	}
	
	@RequestMapping(value = "/allTeachers", method = RequestMethod.GET)
	public String getAllTeachers(Model model){
		List<Teacher> list = teacherService.get();
		model.addAttribute("teachers", list);
		return "allteachers";
	}
}
