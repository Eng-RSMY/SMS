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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.dream.model.User;
import com.dream.service.TeacherService;
import com.dream.service.UserService;
import com.dream.utils.PassEncoding;
import com.dream.utils.Roles;
import com.dream.utils.TypeUser;

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
	
	@Autowired
	private TeacherService teacherService;
	
	//Login
	@GetMapping({"/","/login"})
	public String login(){
		return "login";
	}
	
	/*
	 * //Login
	 * 
	 * @RequestMapping(value="/login", method=RequestMethod.POST) public String
	 * getPage(@ModelAttribute User user, Model model){ model.addAttribute("user",
	 * user); logger.info(user + " Login Successfully.."); return "login"; }
	 */
	
	//Returns register page
	@GetMapping("/register")
	public String hello(){
		return "register";
	}
	
	//It registers the user.
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public String userRegistration(@ModelAttribute User reqUser, final RedirectAttributes redirectAttributes){
		User user = userService.findByName(reqUser.getName());
        if (user != null) {
            redirectAttributes.addFlashAttribute("saveUser", "exist-name");
            logger.warn(user.getName() + " Alredy Exits..! ");
            return "redirect:/register";
        }
        user = userService.findByEmail(reqUser.getEmail());
        if (user != null) {
            redirectAttributes.addFlashAttribute("saveUser", "exist-email");
            logger.warn(user.getEmail() + " Alredy Exits..! ");
            return "redirect:/register";
        }

        reqUser.setPassword(PassEncoding.getInstance().passwordEncoder.encode(reqUser.getPassword()));
        reqUser.setRole(Roles.ROLE_USER.getValue());

        if (userService.save(reqUser) != null) {
            redirectAttributes.addFlashAttribute("saveUser", "success");
            if(reqUser.getTypeUser() == TypeUser.Teacher.getValue()) {
            	teacherService.insertTeacher(reqUser);
            }else if(reqUser.getTypeUser() == TypeUser.Student.getValue()){
            	
            }
            logger.info(reqUser.getName() + " Save Successfully..");
        } else {
        	logger.warn(user + " Not Save Successfully..");
            redirectAttributes.addFlashAttribute("saveUser", "fail");
        }

        return "redirect:/register";
	}
	
	@GetMapping("/secured/hello")
	@ResponseBody
	public String sercuredHello(){
		return "Admin Login";
	}
	
	@GetMapping("/home")
	public String message(Authentication auth, Model m){
		User user = userService.findByName(auth.getName());
		int user_type = user.getTypeUser();
		if(user_type == TypeUser.HM.getValue()) {
			return "hmhome";
		}else if(user_type == TypeUser.Teacher.getValue()){
			List<User> list = userService.findAll();
			m.addAttribute("userList", list);
			return "teacher";
		}else if(user_type == TypeUser.Attender.getValue()) {
			return "attender";
		}else if(user_type == TypeUser.Parent.getValue()){
			return "parent";
		}else if(user_type == TypeUser.Student.getValue()) {
			return "student";
		}
		return null;
	}
	
	@RequestMapping(value = "allTeacherslist", method = RequestMethod.GET)
	public String getAllTeachersList(Model model) {
		List<User> list = userService.findAll()
				.stream()
				.filter(l -> l.getTypeUser()==TypeUser.Teacher.getValue())
				.collect(Collectors.toList());
		model.addAttribute("userList", list);
		return "allteachers";
	}
}
