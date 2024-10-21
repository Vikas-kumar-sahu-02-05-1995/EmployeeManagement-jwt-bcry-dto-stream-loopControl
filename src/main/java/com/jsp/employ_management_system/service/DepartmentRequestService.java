package com.jsp.employ_management_system.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jsp.employ_management_system.dto.DepartmentRequest;

@Service
public  interface DepartmentRequestService {

	public DepartmentRequest createDepartment(DepartmentRequest departmentDTO);
	
	public DepartmentRequest getDepartmentById(Long id);
	public List<DepartmentRequest> getAllDepartments();
	public DepartmentRequest updateDepartment(Long id, DepartmentRequest updatedepartment);
	public  DepartmentRequest deleteDepartmentById(Long id);
	
	public List<DepartmentRequest> sortByDepartmentName(); 
}
