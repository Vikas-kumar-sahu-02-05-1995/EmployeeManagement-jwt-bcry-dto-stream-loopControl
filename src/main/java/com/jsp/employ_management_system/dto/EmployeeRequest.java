package com.jsp.employ_management_system.dto;

import java.util.Date;
import java.util.List;

import com.jsp.employ_management_system.entity.AddressOfEmployee;
import com.jsp.employ_management_system.entity.Department;
import com.jsp.employ_management_system.entity.HR;
import com.jsp.employ_management_system.entity.Task;

import lombok.Data;

@Data
public class EmployeeRequest {

	


	private int empId;
	private String name;
	private Date dateOfJoining;
	private long salary;
	private String email;
	private String passwords;
	private Long managerId;
	
	private List<AddressOfEmployee> addressOfEmployees; 

	private Department department2;

	private List<Task> tasks;	

	 private HR hr1;

}
