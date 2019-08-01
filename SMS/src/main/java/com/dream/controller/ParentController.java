package com.dream.controller;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.dream.model.Parent;
import com.dream.model.Student;
import com.dream.model.User;
import com.dream.service.ParentService;
import com.dream.service.StudentService;
import com.dream.service.UserService;
import com.dream.utils.PassEncoding;
import com.dream.utils.Roles;
/**
 * 
 * @author dileep
 * 
 *	ParentController is created on 25/07/2019.
 */
@Controller
public class ParentController {

	private static final Logger logger = LogManager.getLogger(ParentController.class);

	@Autowired
	private ParentService parentService;

	@Autowired
	private UserService userService;

	@Autowired
	private StudentService studentService;

	// Returns register page of Parent
	@GetMapping("/parentRegister")
	public String parentRegistration() {
		return "parentRegister";
	}

	// parent registration
	@RequestMapping(value = "/parentRegister", method = RequestMethod.POST)
	public String userRegistration(@ModelAttribute User reqUser, @ModelAttribute Parent parent,
			final RedirectAttributes redirectAttributes) {
		logger.info("user : " + reqUser.getId());
		User actualUser = new User();
		actualUser.setEmail(reqUser.getEmail()).setLastName(reqUser.getLastName()).setName(reqUser.getName())
				.setRole(reqUser.getRole())
				.setPassword(PassEncoding.getInstance().passwordEncoder.encode(reqUser.getPassword()))
				.setRole(Roles.Parent.getValue());
		Student stu = studentService.findByUser(userService.findById(reqUser.getId()));
		logger.info("Student : "+stu);
		
		 if(stu.getParent().getId() > 0) { 
			 redirectAttributes.addFlashAttribute("parent", "exist");
			 logger.warn("That Student Already have one Parent");
			 return "redirect:/parentRegister"; 
		 }
		 boolean flag = false;
		 try {
			 flag = userService.save(actualUser);
		 }catch(Exception e) {
			 redirectAttributes.addFlashAttribute("user", "exist");
			 return "redirect:/parentRegister";
		 }
		 
		if (flag) {
			parent.setUser(actualUser);
			parentService.save(parent);
			User n = userService.findUserByFullName(reqUser.getName(), reqUser.getLastName());
			System.out.println(n);
			Parent p = parentService.findByUser(n);
			System.out.println("Parent : "+p);
			stu.setParent(p);
			studentService.insertStudent(stu);
			redirectAttributes.addFlashAttribute("saveUser", "success");
			logger.info(reqUser.getName() + " Save Successfully..");
		} else {
			logger.warn(reqUser + " Not Save Successfully..");
			redirectAttributes.addFlashAttribute("saveUser", "fail");
		}

		return "redirect:/parentRegister";
	}

	// This method returns the edit page based on id.
	@RequestMapping(value = "/parentDetails/{id}", method = RequestMethod.GET)
	public String getParentDetails(@PathVariable("id") int id, Model model) {
		Parent Parent = parentService.getParentById(id);
		logger.info("Parent Info : "+Parent.toString());
		model.addAttribute("Parent", Parent);
		return "editParent";
	}
	
	//This method updates the Parent.
	@RequestMapping(value = "/editParent", method = RequestMethod.POST)
	public String updateParent(@ModelAttribute Parent reqParent, @ModelAttribute User reqUser, Model model) {
		if (reqUser != null && reqParent != null) {
			User actualUser = userService.findById(reqParent.getUser().getId());
			logger.info("User info : "+actualUser.toString());
			if (actualUser != null) {
				actualUser.setName(reqUser.getName()).setLastName(reqUser.getLastName()).setEmail(reqUser.getEmail());
				userService.save(actualUser);
				logger.info("User data Updated..!");
			}
			Parent actualParent = parentService.getParentById(reqParent.getId());
			logger.info(actualParent.toString());
			if (actualParent != null) {
				actualParent.setProfession(reqParent.getProfession())
							.setGender(reqParent.getGender())
							.setPhone(reqParent.getPhone());
				parentService.save(actualParent);
				logger.info("Parent data Updated..!");
				return "redirect:/allParents";
			}
		}
		return "redirect:/allParents";
	}

	//This method returns List of Parents
	@RequestMapping(value = "/parentList", method = RequestMethod.GET)
	public String getAllTeachers(Model model) {
		List<Parent> list = parentService.get();
		model.addAttribute("parents", list);
		return "allParents";
	}

	//This method returns List of Parents in Pageable formate.
	@RequestMapping(value = "/allParents", method = RequestMethod.GET)
	public String getAllParents(@PageableDefault(size = 5) Pageable pageable, Model model) {
		Page<Parent> page = parentService.getPaginated(pageable);
		model.addAttribute("page", page);
		logger.info(page.getTotalElements());
		return "allParents";
	}
}
