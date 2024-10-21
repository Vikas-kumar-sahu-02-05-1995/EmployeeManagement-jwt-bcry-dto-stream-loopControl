package com.jsp.employ_management_system.Response;

import java.util.List;

import com.jsp.employ_management_system.entity.Employee;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseStructure {
	
	private List<Employee> employeeslist;
	private long countOfEmp;
	private int pagecount;
	
}
