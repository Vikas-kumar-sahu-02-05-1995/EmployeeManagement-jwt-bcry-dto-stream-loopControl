package com.jsp.employ_management_system.responseController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jsp.employ_management_system.Response.ResponseStructure;
import com.jsp.employ_management_system.entity.Employee;
import com.jsp.employ_management_system.serviceResponse.ResponseService;

@Controller
@RequestMapping("response")
public class ResponseStructureController {

	@Autowired
	private  ResponseService responseService;

	
	@GetMapping
	public ResponseStructure getEmployees(@RequestParam(defaultValue = "1") int page,
			@RequestParam(defaultValue = "2") int size) {
		List<Employee> employees = responseService.getEmployee(page, size);
		long countOfEmp = responseService.getEmployeeCount();
		int pageCount = (int) Math.ceil((double) countOfEmp / size);
		
		return new ResponseStructure(employees, countOfEmp, pageCount);}

}
