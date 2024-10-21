package com.jsp.employ_management_system.responseController;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jsp.employ_management_system.dto.AttendanceDto;
import com.jsp.employ_management_system.dto.EmployeeRequest;
import com.jsp.employ_management_system.dto.TaskDto;
import com.jsp.employ_management_system.entity.Attendance;
import com.jsp.employ_management_system.entity.Task;
import com.jsp.employ_management_system.model.JwtService;
import com.jsp.employ_management_system.serviceResponse.AttendanceImplService;
import com.jsp.employ_management_system.serviceResponse.EmployeeResponseService;
import com.jsp.employ_management_system.serviceResponse.TaskService;

@Controller
@CrossOrigin("http://localhost:5173")
@RequestMapping("/employee")
public class EmployeeRequestController {

	@Autowired
	private TaskService taskService;
	@Autowired
	private EmployeeResponseService services;
	@Autowired
	private JwtService jwtService;
	@Autowired
	private AttendanceImplService attendanceImplService;
	@Autowired
	private AuthenticationManager authenticationManager;

	@GetMapping("/profile")
	public ResponseEntity<String> employeeProfile(){
		return ResponseEntity.ok("Employee Profile");
	}
	@PostMapping("/login")
	public ResponseEntity<String> loginEmployeeController(@RequestBody EmployeeRequest employeeRequest){
		try {
//			  System.out.println("Email: " + employeeRequest.getEmail()); // Debugging line
//		        System.out.println("Password: " + employeeRequest.getPasswords()); // Be careful with logging passwords!

			 Authentication authentication = authenticationManager.authenticate(
		                new UsernamePasswordAuthenticationToken(employeeRequest.getEmail(), employeeRequest.getPasswords()));
			 UserDetails details = (UserDetails) authentication.getPrincipal();
//			  System.out.println(details);
			 List<String> roles = details.getAuthorities().stream()
					 .map(authority -> authority.getAuthority())
					 .collect(Collectors.toList());
//			 System.out.println(roles);
			 String token = jwtService.generateToken(details.getUsername(), roles);
			 
//			 System.out.println(token);
			 return ResponseEntity.ok(token);
			}catch (Exception e) {
				e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("login failed");
		}
	}
	
	@PreAuthorize("hasAnyRole('EMPLOYEE','MANAGER')")
	@GetMapping("/details")
	public ResponseEntity<EmployeeRequest> getEmployeeDetails() {
		EmployeeRequest employeedetail = services.getLoggedInEmployeeDetails();
		return ResponseEntity.ok(employeedetail);
	}
	
	
	@PreAuthorize("hasAnyRole('EMPLOYEE', 'MANAGER')")
	@GetMapping("/getByEMpId")
	public ResponseEntity<EmployeeRequest> getByIdOfEmployeecontroller() {
		return new ResponseEntity<EmployeeRequest>(services.getByIdOfEmployee(), HttpStatus.OK);
	}
	@PreAuthorize("hasAnyRole('EMPLOYEE','MANAGER')")
	@PostMapping("/saveAttendance")
	public ResponseEntity<AttendanceDto> saveAttendanceByEmpIdcontroller(@RequestBody AttendanceDto attendancedto){
		return new ResponseEntity<AttendanceDto>(services.saveAttendanceByEmpId(attendancedto), HttpStatus.OK);
	}
	
	@PreAuthorize("hasAnyRole('EMPLOYEE','MANAGER')")
	@GetMapping("/attendence/{empId}")
	public ResponseEntity<List<Attendance>> getAttendancesByEmployeeId(@PathVariable int empId) {
	     List<Attendance> attendances = attendanceImplService.getAttendancesByEmployeeId(empId);
	     return new ResponseEntity<>(attendances, HttpStatus.OK);
	 }

	

		@PreAuthorize("hasRole('MANAGER')")
	    @PostMapping("/add")
	    public ResponseEntity<Task> addTask(@RequestBody TaskDto taskRequest) {
	        Task createdTask = taskService.addTask(taskRequest);
	        return ResponseEntity.ok(createdTask);
	    }
		
}
