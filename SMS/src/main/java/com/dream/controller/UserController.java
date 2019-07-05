package com.dream.controller;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.dream.model.User;
import com.dream.service.UserService;
import com.dream.utils.PassEncoding;
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
            logger.info(user + " Save Successfully..");
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
	public String message(Authentication auth){
		User user = userService.findByName(auth.getName());
		int user_type = user.getTypeUser();
		if(user_type == 1) {
			return "hmhome";
		}else if(user_type == 2){
			return "teacher";
		}else if(user_type == 3) {
			return "attender";
		}else if(user_type == 4){
			return "parent";
		}else if(user_type == 5) {
			return "student";
		}
		return null;
	}
}
