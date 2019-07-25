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
import com.dream.model.Teacher;
import com.dream.model.User;
import com.dream.service.ParentService;
import com.dream.service.StudentService;
import com.dream.service.UserService;
import com.dream.utils.PassEncoding;

@Controller
public class ParentController {

	private static final Logger logger = LogManager.getLogger(ParentController.class);
	
	@Autowired
	private ParentService parentService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private StudentService studentService;
	
	//Returns register page of Parent
	@GetMapping("/parentRegister")
	public String parentRegistration() {
		return "parentRegister";
	}
	//parent registration
	@RequestMapping(value="/parentRegister", method=RequestMethod.POST)
	public String userRegistration(@ModelAttribute User reqUser,@ModelAttribute Parent parent,@ModelAttribute Student student, final RedirectAttributes redirectAttributes){
		User user = userService.findByName(reqUser.getName());
        user = userService.findByEmail(reqUser.getEmail());
        if (user != null) {
            redirectAttributes.addFlashAttribute("saveUser", "exist-email");
            logger.warn(user.getEmail() + " Alredy Exits..! ");
            return "redirect:/parentRegister";
        }

        reqUser.setPassword(PassEncoding.getInstance().passwordEncoder.encode(reqUser.getPassword()));

        if (userService.save(reqUser) != null) {
            redirectAttributes.addFlashAttribute("saveUser", "success");
            	parent.setUser(reqUser);
            	Student st = studentService.getStudentById(student.getId());
            	st.setParent(parent);
            	parentService.save(parent);
            	studentService.insertStudent(st);
            logger.info(reqUser.getName() + " Save Successfully..");
        } else {
        	logger.warn(user + " Not Save Successfully..");
            redirectAttributes.addFlashAttribute("saveUser", "fail");
        }

        return "redirect:/parentRegister";
	}
	@RequestMapping(value = "/parentDetails/{id}", method = RequestMethod.GET)
	public String getParentDetails(@PathVariable("id") int id, Model model) {
		Parent Parent = parentService.getParentById(id);
		System.out.println(Parent.toString());
		model.addAttribute("Parent", Parent);
		return "editParent";
	}
	
	@RequestMapping(value = "/editParent", method = RequestMethod.POST)
	public String updateParent(@ModelAttribute Parent reqParent, @ModelAttribute User reqUser,Model model) {
		if(reqUser != null && reqParent != null) {
			User actualUser = userService.findById(reqParent.getUser().getId());
			System.out.println(actualUser.toString());
			if(actualUser != null) {
				actualUser.setName(reqUser.getName())
							.setLastName(reqUser.getLastName())
							.setEmail(reqUser.getEmail());
				userService.save(actualUser);
				System.out.println("User data Updated..!");
			}
			Parent actualParent = parentService.getParentById(reqParent.getId());
			System.out.println(actualParent.toString());
			if(actualParent != null) {
				actualParent.setProfession(reqParent.getProfession());
				parentService.save(actualParent);
				System.out.println("Parent data Updated..!");
				model.addAttribute("msg", "success");
				return "editParent";
			}
		}
		model.addAttribute("msg", "fail");
		return "editParent";
	}
	
	@RequestMapping(value = "/parentList", method = RequestMethod.GET)
	public String getAllTeachers(Model model){
		List<Parent> list = parentService.get();
		model.addAttribute("parents", list);
		return "allParents";
	}
	
	@RequestMapping(value = "/allParents", method = RequestMethod.GET)
	public String getAllParents(@PageableDefault(size = 5) Pageable pageable, Model model) {
		Page<Parent> page = parentService.getPaginated(pageable);
		model.addAttribute("page", page);
		System.out.println(page.getTotalElements());
		return "allParents";
	}
}
