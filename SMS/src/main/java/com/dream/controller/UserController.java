package com.dream.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dream.model.User;
import com.dream.service.UserService;
import com.dream.utils.Roles;

/**
 * User controller Created by Dileep on 16/05/2019
 * 
 * @author dileep
 *
 */
@Controller
public class UserController {

	private static final Logger logger = LogManager.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;
	
	//Login
	@GetMapping({"/","/login"})
	public String login(){
		return "login";
	}
	
	//Home page Based on role
	@GetMapping("/home")
	public String message(Authentication auth, Model m){
		User user = userService.findByName(auth.getName());
		int user_type = user.getRole();
		if(user_type == Roles.HM.getValue()) {
			return "hmhome";
		}else if(user_type == Roles.Teacher.getValue()){
			List<User> list = userService.findAll();
			m.addAttribute("userList", list);
			return "teacher";
		}else if(user_type == Roles.Attender.getValue()) {
			return "attender";
		}else if(user_type == Roles.Parent.getValue()){
			return "parent";
		}else if(user_type == Roles.Student.getValue()) {
			return "student";
		}
		return null;
	}
	
	//List of Teachers.
	@RequestMapping(value = "allTeacherslist", method = RequestMethod.GET)
	public String getAllTeachersList(Model model) {
		List<User> list = userService.findAll()
				.stream()
				.filter(l -> l.getRole()==Roles.Teacher.getValue())
				.collect(Collectors.toList());
		model.addAttribute("userList", list);
		return "allteachers";
	}
}
