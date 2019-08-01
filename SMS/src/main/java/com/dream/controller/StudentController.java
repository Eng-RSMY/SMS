package com.dream.controller;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.dream.model.Student;
import com.dream.model.Teacher;
import com.dream.model.User;
import com.dream.service.StudentService;
import com.dream.service.UserService;
import com.dream.utils.PassEncoding;
import com.dream.utils.Roles;

/**
 * StudentController Created by Dileep on 17/07/2019
 * 
 * @author dileep
 *
 */
@Controller
public class StudentController {

	private static final Logger logger = LogManager.getLogger(StudentController.class);

	@Autowired
	private StudentService studentService;

	@Autowired
	private UserService userService;

	// Returns register page of Students
	@GetMapping("/studentRegister")
	public String studentRegistration() {
		return "studentRegister";
	}

	// Student Registration
	@RequestMapping(value = "/studentRegister", method = RequestMethod.POST)
	public String studentRegistration(@ModelAttribute User reqUser, @ModelAttribute Teacher teacher,
			@ModelAttribute Student student, final RedirectAttributes redirectAttributes) {
		User user = userService.findByName(reqUser.getName());
		reqUser.setPassword(PassEncoding.getInstance().passwordEncoder.encode(reqUser.getPassword()))
				.setRole(Roles.Student.getValue());
		boolean flag = false;
		try {
			flag = userService.save(reqUser);
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("user", "exist");
			return "redirect:/studentRegister";
		}
		if (flag) {
			redirectAttributes.addFlashAttribute("saveUser", "success");
			student.setUser(reqUser);
			studentService.insertStudent(student);
			logger.info(reqUser.getName() + " Save Successfully..");
		} else {
			logger.warn(user + " Not Save Successfully..");
			redirectAttributes.addFlashAttribute("saveUser", "fail");
		}
		return "redirect:/studentRegister";
	}

	// This method returns edit page of Student based on student id
	@RequestMapping(value = "/studentDetails/{id}", method = RequestMethod.GET)
	public String getStudentDetails(@PathVariable("id") int id, Model model) {
		Student student = studentService.getStudentById(id);
		logger.info(student.toString());
		model.addAttribute("student", student);
		return "editStudent";
	}

	// This method updates the edited data
	@RequestMapping(value = "/editStudent", method = RequestMethod.POST)
	public String updateStudent(@ModelAttribute Student reqStudent, @ModelAttribute User reqUser, Model model) {
		if (reqUser != null && reqStudent != null) {
			User actualUser = userService.findById(reqStudent.getUser().getId());
			logger.info(actualUser.toString());
			if (actualUser != null) {
				actualUser.setName(reqUser.getName()).setLastName(reqUser.getLastName()).setEmail(reqUser.getEmail());
				userService.save(actualUser);
				logger.info("User data Updated..!");
			}
			Student actualStudent = studentService.getStudentById(reqStudent.getId());
			logger.info(actualStudent.toString());
			if (actualStudent != null) {
				actualStudent.setDob(reqStudent.getDob()).setClassOfStudy(reqStudent.getClassOfStudy())
						.setGender(reqStudent.getGender()).setSection(reqStudent.getSection());
				studentService.insertStudent(actualStudent);
				logger.info("Student data Updated..!");
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
		logger.info(page.getTotalElements());
		return "allStudents";
	}

	@RequestMapping(value = "/getStudents/{class}", method = RequestMethod.GET)
	public String getStudentsByClass(@PathVariable("class") int classOfStudy, Model model) {
		logger.info("@@ getStudents/" + classOfStudy + " Calling...");
		List<Student> students = new ArrayList<Student>();
		students = studentService.getAllStudents(classOfStudy);
		students.forEach(System.out::println);
		model.addAttribute("students", students);
		return "attendance";

	}
	
	@RequestMapping(value = "/getAllStudents/{class}", method = RequestMethod.GET)
	public String getStudents(@PathVariable("class") int classOfStudy, Model model) {
		logger.info("@@ getStudents/" + classOfStudy + " Calling...");
		List<Student> students = new ArrayList<Student>();
		students = studentService.getAllStudents(classOfStudy);
		students.forEach(System.out::println);
		model.addAttribute("classOfStudy", classOfStudy);
		model.addAttribute("students", students);
		return "students";

	}
}
