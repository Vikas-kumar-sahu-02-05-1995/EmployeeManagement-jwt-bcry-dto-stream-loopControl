package com.jsp.employ_management_system.dto;

import java.util.List;

import com.jsp.employ_management_system.entity.Employee;
import com.jsp.employ_management_system.entity.HR;

import lombok.Data;

@Data
public class DepartmentRequest {

	private Long id;
	private String departmentName;

	private List<Employee> employees;
	private HR hr;
}
