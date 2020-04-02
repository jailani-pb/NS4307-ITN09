package com.nep.onlinelesson.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nep.onlinelesson.data.EmployeeRepository;
import com.nep.onlinelesson.model.Employee;

@Controller
public class EmployeeController {

	@Autowired
	EmployeeRepository empRepo;
	int currentEmployeeId;
	
	@RequestMapping(value="/")
	public String employees(ModelMap modelMap) {
		Iterable<Employee> employeeList = empRepo.findAll();
		modelMap.put("employees", employeeList);
		return "employees";
	}
	
	@RequestMapping(value="/addemployee")
	public String showAddEmployeePage() {
		return "add_employee";
	}
	
	@RequestMapping(value="/addemployee/add")
	public String addEmployee(
			@RequestParam(required=true) int employee_id,
			@RequestParam(required=true) String full_name,
			@RequestParam(required=true) String ic_number,
			@RequestParam(required=true) String start_date,
			@RequestParam(required=true) double salary
			) throws ParseException {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Calendar startDate = Calendar.getInstance();
		startDate.setTime(df.parse(start_date));
		Employee employee = new Employee(employee_id, ic_number, full_name, salary, startDate);
		empRepo.save(employee);
		return "redirect:/";
	}
	
	@RequestMapping(value="/editemployee/{employeeId}")
	public String showEditEmployeePage(@PathVariable int employeeId, ModelMap modelMap) {
		Employee employee = empRepo.findById(employeeId).get();
		modelMap.put("employee", employee);
		currentEmployeeId = employeeId;
		return "edit_employee";
	}
	
	@RequestMapping(value="/editemployee/{employeeId}/edit")
	public String editEmployee(
			@RequestParam(required=true) String full_name,
			@RequestParam(required=true) String ic_number,
			@RequestParam(required=true) String start_date,
			@RequestParam(required=true) double salary,
			@PathVariable int employeeId
			) throws ParseException {
		if(currentEmployeeId == employeeId) {
			Employee employee = empRepo.findById(employeeId).get();
			employee.setName(full_name);
			employee.setIcNumber(ic_number);
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			Calendar startDate = Calendar.getInstance();
			startDate.setTime(df.parse(start_date));
			employee.setStartDate(startDate);
			employee.setSalary(salary);
			empRepo.save(employee);
		}
		return "redirect:/";
	}
	
	@RequestMapping(value="/deleteemployee/{employeeId}")
	public String deleteEmployee(@PathVariable int employeeId) {
		empRepo.deleteById(employeeId);
		return "redirect:/";
	}
	
}
