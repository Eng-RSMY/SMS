package com.dream.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dream.model.Student;
import com.dream.service.StudentServiceImpl;

@Controller
public class StudentController {

	@Autowired
	private StudentServiceImpl studentService;
	
	@RequestMapping(value = "/studentDetails/{id}", method = RequestMethod.GET)
	public String getStudentDetails(@PathVariable("id") int id, Model model) {
		Student teacher = studentService.getStudentById(id);
		model.addAttribute("teacher", teacher);
		return "";
	}
	
	@RequestMapping(value = "studentList", method = RequestMethod.GET)
	public String getAllTeachers(Model model){
		List<Student> list = studentService.get();
		model.addAttribute("students", list);
		return "allStudents";
	}
	
	@RequestMapping(value = "/allStudents", method = RequestMethod.GET)
	public String getAllStudents(@PageableDefault(size = 5) Pageable pageable, Model model) {
		Page<Student> page = studentService.getPaginated(pageable);
		model.addAttribute("page", page);
		System.out.println(page.getTotalElements());
		return "allStudents";
	}
}
