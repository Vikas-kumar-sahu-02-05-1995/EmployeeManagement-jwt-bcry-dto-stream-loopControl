package com.jsp.employ_management_system.serviceResponse;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.jsp.employ_management_system.entity.Employee;
import com.jsp.employ_management_system.repository.EmployeeRepository;

@Service
public class ResponseService {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	public List<Employee> getEmployee(int page, int size){
		return employeeRepository.findAll(PageRequest.of(page, size)).toList();
	}
	
	public long getEmployeeCount() {
		return employeeRepository.count();
	}
	
}
