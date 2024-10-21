package com.jsp.employ_management_system.dto;

import java.time.LocalDateTime;

import com.jsp.employ_management_system.entity.Employee;

import lombok.Data;

@Data
public class TaskDto {

	private Long id;
	private String title;
	private String description;
	private LocalDateTime createdDate;
	private LocalDateTime dueDate;
	private int empId;
	
	private Employee employee;
}
