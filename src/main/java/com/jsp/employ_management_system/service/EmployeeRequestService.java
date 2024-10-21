package com.jsp.employ_management_system.service;

import org.springframework.stereotype.Service;

import com.jsp.employ_management_system.entity.Employee;

@Service
public interface EmployeeRequestService {

	public Employee getDetails(Employee employee);
	
}
