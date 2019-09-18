package com.dream.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.dream.model.Marks;
import com.dream.model.Student;
import com.dream.model.Teacher;
import com.dream.model.User;
import com.dream.service.MarksService;
import com.dream.service.StudentService;
import com.dream.service.TeacherService;
import com.dream.service.UserService;

@Controller
public class MarksController {

	@Autowired
	private MarksService marksService;

	@Autowired
	private UserService userService;

	@Autowired
	private TeacherService teacherService;

	@Autowired
	private StudentService studentService;

	private String subject;
	/*
	 * @RequestMapping(value = "/getAllMarks/{class}", method = RequestMethod.GET)
	 * public String getStudentsMarks(@PathVariable("class") int classOfStudy, Model
	 * model, Authentication auth) { // logger.info("@@ getAllStudentsMarks/" +
	 * classOfStudy + " Calling..."); List<Marks> marks = new ArrayList<>(); User u
	 * = userService.findByName(auth.getName()); System.out.println(u.getId());
	 * Teacher t = teacherService.getTeacherByUserId(u);
	 * model.addAttribute("subject", t.getSubject()); marks =
	 * marksService.getMarksList(classOfStudy); marks.forEach(l -> { Marks m = l;
	 * System.out.println(m.getStud().toString()); }); model.addAttribute("marks",
	 * marks); return "marks"; }
	 */
	
	@RequestMapping(value = "/getAllMarks/{class}", method = RequestMethod.GET)
	public String getMarks(@PathVariable("class") int classOfStudy, Model model, Authentication auth) {
		//logger.info("@@ getAllStudentsMarks/" + classOfStudy + " Calling...");
		List<Student> students = new ArrayList<Student>();
		User u = userService.findByName(auth.getName());
		System.out.println(u.getId());
		Teacher t = teacherService.getTeacherByUserId(u);
		subject = t.getSubject();
		model.addAttribute("subject", subject);
		students = studentService.getAllStudents(classOfStudy);
		students.forEach(l ->{
			if(l.getMarks()!=null) {
				System.out.println(l.getMarks().getHindi());
				System.out.println(l.getMarks().getTelugu());
			}
		});
		model.addAttribute("classOfStudy", classOfStudy);
		model.addAttribute("students", students);
		return "marks";

	}

	@RequestMapping(value = "/editMarks/{id}", method = RequestMethod.GET)
	public String getMarksRecord(@PathVariable("id") int id, Model model) {
		Marks marks = marksService.getMarksByStudent(id);
		if(marks==null) {
			marks = new Marks();
		}
		float value = 0;
		if(subject.equalsIgnoreCase("Telugu")) {
			value = marks.getTelugu();
			System.out.println("value of marks telugu: "+value);
		}else if(subject.equalsIgnoreCase("Hindi")) {
			value = marks.getHindi();
			System.out.println("value of marks hindi : "+value);
		}else if(subject.equalsIgnoreCase("English")) {
			value = marks.getEnglish();
			System.out.println("value of marks english: "+value);
		}else if(subject.equalsIgnoreCase("Maths")) {
			value = marks.getHindi();
			System.out.println("value of marks hindi : "+value);
		}else if(subject.equalsIgnoreCase("Science")) {
			value = marks.getEnglish();
			System.out.println("value of marks english: "+value);
		}else if(subject.equalsIgnoreCase("Social")) {
			value = marks.getEnglish();
			System.out.println("value of marks english: "+value);
		}
		System.out.println("value of marks : "+value);
		Student s = studentService.getStudentById(id);
		model.addAttribute("marks", value);
		model.addAttribute("student", s);
		model.addAttribute("subject", subject);
		return "editMarks";
	}
	
	@RequestMapping(value = "/editMarks", method = RequestMethod.POST)
	public String saveMarks(@RequestParam("mark") float marks,@ModelAttribute Student stu) {
		Marks m = marksService.getMarksByStudent(stu.getId());
		if(m == null) {
			m = new Marks();
			Student s = studentService.getStudentById(stu.getId());
			m.setStud(s);
		}
		if(subject.equalsIgnoreCase("telugu")) {
			m.setTelugu(marks);
		}else if(subject.equalsIgnoreCase("english")) {
			m.setEnglish(marks);
		}else if(subject.equalsIgnoreCase("hindi")) {
			m.setHindi(marks);
		}else if(subject.equalsIgnoreCase("Maths")) {
			m.setMaths(marks);
		}else if(subject.equalsIgnoreCase("Science")) {
			m.setScience(marks);
		}else if(subject.equalsIgnoreCase("Social")) {
			m.setSocial(marks);
		}
		marksService.save(m);
		return "redirect:/getAllMarks/"+stu.getClassOfStudy()+"";
	}
	
}
