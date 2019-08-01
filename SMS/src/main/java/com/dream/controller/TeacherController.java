package com.dream.controller;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.dream.model.Teacher;
import com.dream.model.User;
import com.dream.service.TeacherService;
import com.dream.service.UserService;
import com.dream.utils.PassEncoding;
import com.dream.utils.Roles;

/**
 * 
 * @author dileep
 * 
 * TeacherController created by Dileep on 15/07/2019
 */
@Controller
public class TeacherController {
	
	private static final Logger logger = LogManager.getLogger(TeacherController.class);
	
	@Autowired
	private TeacherService teacherService;
	
	@Autowired
	private UserService userService;
	
	//Returns register page of Teachers
	@GetMapping("/teacherRegister")
	public String registration(){
		return "teacherRegister";
	}
	
	//It registers the Teacher.
		@RequestMapping(value="/teacherRegister", method=RequestMethod.POST)
		public String userRegistration(@ModelAttribute User reqUser, @ModelAttribute Teacher teacher, final RedirectAttributes redirectAttributes){
			User user = userService.findByName(reqUser.getName());
	        user = userService.findByEmail(reqUser.getEmail());
	        if (user != null) {
	            redirectAttributes.addFlashAttribute("saveUser", "exist-email");
	            logger.warn(user.getEmail() + " Alredy Exits..! ");
	            return "redirect:/teacherRegister";
	        }

	        reqUser.setPassword(PassEncoding.getInstance().passwordEncoder.encode(reqUser.getPassword()))
	        		.setRole(Roles.Teacher.getValue());
	        boolean flag = false;
			 try {
				 flag = userService.save(reqUser);
			 }catch(Exception e) {
				 redirectAttributes.addFlashAttribute("user", "exist");
				 return "redirect:/parentRegister";
			 }
	        if (flag) {
	            redirectAttributes.addFlashAttribute("saveUser", "success");
	            	teacher.setUser(reqUser);
	            	teacherService.insertTeacher(teacher);
	            logger.info(reqUser.getName() + " Save Successfully..");
	        } else {
	        	logger.warn(user + " Not Save Successfully..");
	            redirectAttributes.addFlashAttribute("saveUser", "fail");
	        }

	        return "redirect:/teacherRegister";
		}
	
	// This method returns the edit page based on id.
	@RequestMapping(value = "/teacherDetails/{id}", method = RequestMethod.GET)
	public String getTeacherDetails(@PathVariable("id") int id, Model model) {
		Teacher teacher = teacherService.getTeacherById(id);
		model.addAttribute("teacher", teacher);
		return "editTeacher";
	}
	
	// This method updates the teacher.
	@RequestMapping(value = "/editTeacher", method = RequestMethod.POST)
	public String updateTeacher(@ModelAttribute Teacher reqTeacher, @ModelAttribute User reqUser, Model model) {
		if(reqUser != null && reqTeacher != null) {
			User actualUser = userService.findById(reqTeacher.getUser().getId());
			logger.info(actualUser.toString());
			if(actualUser != null) {
				actualUser.setName(reqUser.getName())
							.setLastName(reqUser.getLastName())
							.setEmail(reqUser.getEmail());
				userService.save(actualUser);
				logger.info("User data Updated..!");
			}
			Teacher actualTeacher = teacherService.getTeacherById(reqTeacher.getId());
			logger.info(actualTeacher.toString());
			if(actualTeacher != null) {
				actualTeacher.setDob(reqTeacher.getDob())
								.setExperience(reqTeacher.getExperience())
								.setGender(reqTeacher.getGender())
								.setLanguage(reqTeacher.getLanguage())
								.setSubject(reqTeacher.getSubject())
								.setPhone(reqTeacher.getPhone());
				teacherService.insertTeacher(actualTeacher);
				logger.info("Teacher data Updated..!");
				model.addAttribute("msg", "success");
				return "editTeacher";
			}
		}
		model.addAttribute("msg", "fail");
		return "editTeacher";
	}
	
	// This method returns all Teachers.
	@RequestMapping(value = "/allTeachers", method = RequestMethod.GET)
	public String getAllTeachers(Model model){
		List<Teacher> list = teacherService.get();
		model.addAttribute("teachers", list);
		return "allteachers";
	}
}
