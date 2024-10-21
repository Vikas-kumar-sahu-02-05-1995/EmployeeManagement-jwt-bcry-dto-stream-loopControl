package com.jsp.employ_management_system.serviceResponse;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.jsp.employ_management_system.dto.AttendanceDto;
import com.jsp.employ_management_system.dto.EmployeeRequest;
import com.jsp.employ_management_system.entity.AddressOfEmployee;
import com.jsp.employ_management_system.entity.Attendance;
import com.jsp.employ_management_system.entity.Employee;
import com.jsp.employ_management_system.repository.AttendanceRepo;
import com.jsp.employ_management_system.repository.DepartmentRepopository;
import com.jsp.employ_management_system.repository.EmployeeRepository;

@Service
@org.springframework.transaction.annotation.Transactional
public class EmployeeResponseService  {
	
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private EmployeeRepository repo;
	@Autowired
	private AttendanceRepo attendanceRepo;
	@Autowired
	private DepartmentServiceImpl departmentServiceImpl;
	@Autowired
    private DepartmentRepopository departmentRepo;
	
	public EmployeeRequest getLoggedInEmployeeDetails() {
		Employee loggedInEMployee = getLoggedInEmployee();
		return mapToEmployeeRequest(loggedInEMployee);
	}
	

	
	private Employee getLoggedInEmployee() {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		return repo.findByEmail(username);
	}
	
	private EmployeeRequest mapToEmployeeResponse(Employee employee) {
		return new EmployeeRequest();
	}
	
	public AttendanceDto saveAttendanceByEmpId( AttendanceDto attendanceDto) {
		Employee saveAttendence = getLoggedInEmployee();
		if (attendanceDto.getAttendanceDate() == null) {
		    throw new IllegalArgumentException("Attendance date must not be null");
		}	
		Attendance attendance = modelMapper.map(attendanceDto, Attendance.class);
		attendance.setEmployee(saveAttendence);
	  
		 System.out.println("Converted Attendance Entity: " + attendance);
		  Attendance savedAttendance = attendanceRepo.save(attendance);
		return mapToAttendanceDto(savedAttendance);
	}
	
	
	
	public EmployeeRequest getByIdOfEmployee() {
		Employee emp = getLoggedInEmployee();
		return mapToEmployeeRequest(emp);
	}
	
	public EmployeeRequest mapToEmployeeRequest(Employee employee) {
		EmployeeRequest request = this.modelMapper.map(employee, EmployeeRequest.class);

	    return request;
	}
	public AttendanceDto mapToAttendanceDto(Attendance attendance) {
		AttendanceDto dto = this.modelMapper.map(attendance, AttendanceDto.class);
		return dto;
	}
//	public TaskDto mapToTaskDto(Task task) {
//		TaskDto dtos = this.modelMapper.map(task, TaskDto.class);
//		return dtos;
//	}
	
	public Employee convertToEntity(EmployeeRequest employeeRequest) {
	    Employee employee = this.modelMapper.map(employeeRequest, Employee.class);
	    
	    if(employee.getAddressOfEmployees() != null) {
	        for(AddressOfEmployee addressOfEmployee : employee.getAddressOfEmployees()) {
	            addressOfEmployee.setEmployee(employee); // Ensures bidirectional mapping
	            System.out.println("Mapped address: " + addressOfEmployee);
	        }
	    } else {
	        System.out.println("No Address found in request.");
	    }
	    return employee;
	}

	public Attendance convertToEntityAttendence(AttendanceDto attendanceDto) {
	    Attendance attendance = this.modelMapper.map(attendanceDto, Attendance.class);

	    // Log converted entity
	    System.out.println("Converted Attendance Entity: " + attendance);

	    return attendance;
	}
	
	public AttendanceDto converttoResponseAttendence(Attendance attendance) {
		AttendanceDto attendanceDto = this.modelMapper.map(attendance, AttendanceDto.class);
		return attendanceDto;
	}
	




}
