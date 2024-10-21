package com.jsp.employ_management_system.dto;

import java.util.Date;
import java.util.List;

import com.jsp.employ_management_system.entity.AddressHr;
import com.jsp.employ_management_system.entity.Department;
import com.jsp.employ_management_system.entity.Employee;

import lombok.Data;

@Data
public class HrRequest {

	private int hrId;
	private String name;
	private String qualification;
	private Date dateOfJoining;
	private int age;
	private String experience;
	private String email;
	private String passwords;

	
	private List<AddressHr> addressesHr;
	private List<Employee> employees;
	private List<Department> department;
//	private Set<Role> roles;
}
