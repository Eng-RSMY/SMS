package com.dream.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.dream.model.Parent;
import com.dream.model.User;
import com.dream.service.ParentService;
import com.dream.service.UserService;

@Controller
public class ParentController {

	@Autowired
	private ParentService parentService;
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/ParentDetails/{id}", method = RequestMethod.GET)
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
