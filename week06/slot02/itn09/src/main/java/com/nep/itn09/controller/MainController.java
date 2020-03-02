package com.nep.itn09.controller;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nep.itn09.model.Student;

@Controller
public class MainController {

	@RequestMapping(value="/")
	public String index(ModelMap modelMap) {
		modelMap.put("moduleId", "NS4306");
		modelMap.put("moduleName", "Mobile Wireless Computing");
		return "index";
	}
	
	@RequestMapping(value="/studentdetails")
	public String studentDetails(ModelMap modelMap) {
		Student student = 
				new Student("18FTT001", "Jailani Rahman", 18);
		modelMap.put("student", student);
		return "studentdetails";
	}
	
	@RequestMapping(value="/students")
	public String students(ModelMap modelMap) {
		ArrayList<Student> students = new ArrayList<Student>();
		students.add(new Student("A1", "Jailani", 20));
		students.add(new Student("A2", "Rahman", 23));
		students.add(new Student("B1", "Abdul", 21));
		students.add(new Student("B2", "Mohammad", 20));
		students.add(new Student("A3", "Haji", 22));
		modelMap.put("students", students);
		return "students";
	}
	
}




