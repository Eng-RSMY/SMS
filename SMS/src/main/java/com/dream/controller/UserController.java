package com.dream.controller;

import java.util.List;
import java.util.stream.Collectors;

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
			m.addAttribute("user_type", "HM");
			return "hmhome";
		}else if(user_type == Roles.Teacher.getValue()){
			List<User> list = userService.findAll();
			m.addAttribute("userList", list);
			m.addAttribute("user_type", "Teacher");
			return "teacher";
		}else if(user_type == Roles.Attender.getValue()) {
			m.addAttribute("user_type", "Attender");
			return "attender";
		}else if(user_type == Roles.Parent.getValue()){
			m.addAttribute("user_type", "Parent");
			return "parent";
		}else if(user_type == Roles.Student.getValue()) {
			m.addAttribute("user_type", "Student");
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
	
	//password change
	@GetMapping("/changePassword")
	public String getChangePassword() {
		return "changePassword"; 
	}
	
	/*
	 * @RequestMapping(value = "/changePasswordPost", method = RequestMethod.POST)
	 * public String updatePassword(Authentication auth, @RequestParam("oldpwd")
	 * String oldpwd, @RequestParam("newpwd") String newpwd, final
	 * RedirectAttributes redirectAttributes) {
	 * logger.info("@@ @@ changePasswordPost method is called....!"); User user =
	 * userService.findByName(auth.getName()); logger.info(user); String
	 * oldpwdEncode = PassEncoding.getInstance().passwordEncoder.encode(oldpwd);
	 * String newpwdEncode =
	 * PassEncoding.getInstance().passwordEncoder.encode(newpwd);
	 * logger.info(auth.getPrincipal().toString());
	 * if(user.getPassword().equals(oldpwdEncode)) { logger.info("@@@ matched....");
	 * userService.save(user.setPassword(newpwdEncode)); return "redirect:/login"; }
	 * redirectAttributes.addFlashAttribute("param", "error"); return
	 * "/changePassword"; }
	 */
}
