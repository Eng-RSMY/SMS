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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dream.model.Student;
import com.dream.model.User;
import com.dream.service.StudentServiceImpl;
import com.dream.service.UserService;

/**
 * StudentController Created by Dileep on 17/07/2019
 * 
 * @author dileep
 *
 */
@Controller
public class StudentController {

	@Autowired
	private StudentServiceImpl studentService;

	@Autowired
	private UserService userService;

	// This method returns edit page of Student based on student id
	@RequestMapping(value = "/studentDetails/{id}", method = RequestMethod.GET)
	public String getStudentDetails(@PathVariable("id") int id, Model model) {
		Student student = studentService.getStudentById(id);
		System.out.println(student.toString());
		model.addAttribute("student", student);
		return "editStudent";
	}

	// This method updates the edited data
	@RequestMapping(value = "/editStudent", method = RequestMethod.POST)
	public String updateStudent(@ModelAttribute Student reqStudent, @ModelAttribute User reqUser, Model model) {
		if (reqUser != null && reqStudent != null) {
			User actualUser = userService.findById(reqStudent.getUser().getId());
			System.out.println(actualUser.toString());
			if (actualUser != null) {
				actualUser.setName(reqUser.getName()).setLastName(reqUser.getLastName()).setEmail(reqUser.getEmail());
				userService.save(actualUser);
				System.out.println("User data Updated..!");
			}
			Student actualStudent = studentService.getStudentById(reqStudent.getId());
			System.out.println(actualStudent.toString());
			if (actualStudent != null) {
				actualStudent.setDob(reqStudent.getDob()).setClassOfStudy(reqStudent.getClassOfStudy())
						.setGender(reqStudent.getGender()).setSection(reqStudent.getSection());
				studentService.insertStudent(actualStudent);
				System.out.println("Student data Updated..!");
				model.addAttribute("msg", "success");
				return "editStudent";
			}
		}
		model.addAttribute("msg", "fail");
		return "editStudent";
	}
	 
	// This method used for fetching the matched data with request parameter.
	@RequestMapping(value = "/students", method = RequestMethod.GET)
	@ResponseBody
	public List<User> getStudents(@RequestParam("q") String st) {
		return userService.findAllUsers(st);
	}

	// This method returns all student data.
	@RequestMapping(value = "/allStudents", method = RequestMethod.GET)
	public String getAllStudents(@PageableDefault(size = 5) Pageable pageable, Model model) {
		Page<Student> page = studentService.getPaginated(pageable);
		model.addAttribute("page", page);
		System.out.println(page.getTotalElements());
		return "allStudents";
	}

}
