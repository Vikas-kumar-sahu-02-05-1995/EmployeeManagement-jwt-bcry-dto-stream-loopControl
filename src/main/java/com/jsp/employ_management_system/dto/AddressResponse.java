package com.jsp.employ_management_system.dto;


import com.jsp.employ_management_system.entity.Employee;

import lombok.Data;

@Data
public class AddressResponse {

	private int addressId;
	private String addressdetail;
	private int employeeId;

	private Employee employee;
}
