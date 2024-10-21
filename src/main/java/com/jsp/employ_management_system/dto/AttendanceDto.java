package com.jsp.employ_management_system.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import com.jsp.employ_management_system.entity.Employee;

import lombok.Data;

@Data
public class AttendanceDto {

private Long id;
	
	private LocalDate attendanceDate;
	private String status; 
	private LocalTime checkInTime; 

	private LocalTime checkOutTime; 

	private String remarks; 
   
    private Employee employee;
}
