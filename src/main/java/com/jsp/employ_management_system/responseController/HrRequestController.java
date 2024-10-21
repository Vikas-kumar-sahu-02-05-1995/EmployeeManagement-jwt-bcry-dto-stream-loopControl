package com.jsp.employ_management_system.responseController;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jsp.employ_management_system.dto.DepartmentRequest;
import com.jsp.employ_management_system.dto.EmployeeRequest;
import com.jsp.employ_management_system.dto.HrRequest;
import com.jsp.employ_management_system.entity.Attendance;
import com.jsp.employ_management_system.model.JwtService;
import com.jsp.employ_management_system.service.HrRequestService;
import com.jsp.employ_management_system.serviceResponse.AttendanceImplService;

@Controller
@CrossOrigin("http://localhost:5173")
@RequestMapping("/hr")
public class HrRequestController {

	@Autowired
	private JwtService jwtService;
	@Autowired 
	private HrRequestService requestService;
	@Autowired
	private AttendanceImplService attendanceImplService;
	@Autowired
	private AuthenticationManager authenticationManager;

	@GetMapping("/profile")
	public ResponseEntity<String> hrProfile(){
		return ResponseEntity.ok("HR Profile");
	}
	
	@PostMapping("/login")
	public ResponseEntity<String> loginController(@RequestBody HrRequest hrRequest) {
	    try {
	        // Authenticate the HR user
	        Authentication authentication = authenticationManager.authenticate(
	                new UsernamePasswordAuthenticationToken(hrRequest.getEmail(), hrRequest.getPasswords()));
	        
	        // Get the authenticated user's details
	        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
	        
	        // Fetch roles and include them in the JWT
	        List<String> roles = userDetails.getAuthorities().stream()
	                .map(authority -> authority.getAuthority())
	                .collect(Collectors.toList());

	        // Generate JWT token after successful authentication
	        String token = jwtService.generateToken(userDetails.getUsername(), roles);

	        // Return the token in the response body
	        return ResponseEntity.ok( token);
	    } catch (Exception e) {
	        e.printStackTrace();
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Credentials");
	    }
	}

	@PreAuthorize("hasRole('HR')")
	@GetMapping("detailsHR")
	public ResponseEntity<HrRequest> getHrDetails(){
		HrRequest hrRequest = requestService.getLoggedInHrDetails();
		return ResponseEntity.ok(hrRequest);
	}

//	@GetMapping("/login")
//	public String loginHrByEmail() {
//		return "login";
//	}
	
	@PreAuthorize("hasRole('HR')")
	@GetMapping("/getAllAttendance")
	 public ResponseEntity<List<Attendance>> getAllAttendances() {
		 List<Attendance> attendances = attendanceImplService.getAllAttendances();
	        return new ResponseEntity<>(attendances, HttpStatus.OK);
	}
	@PreAuthorize("hasRole('HR')")
	@PostMapping("/saveDepartment")
	public ResponseEntity<DepartmentRequest> saveDepartmentByHrId(@RequestBody DepartmentRequest hrreq) {
		return new ResponseEntity<DepartmentRequest>(requestService.saveDepartmentByHrLogin(hrreq), HttpStatus.OK);
	}
	@PreAuthorize("hasRole('HR')")
	@PostMapping("/insert")
	public ResponseEntity<HrRequest> saveHrRequestController(@RequestBody HrRequest hrRequest) {
		return new ResponseEntity<HrRequest>(requestService.saveHrRequest(hrRequest), HttpStatus.OK);
	}
	@PreAuthorize("hasRole('HR')")
	@GetMapping("/getAllHr")
	public ResponseEntity<List<HrRequest>> getListOfHrController() {
		return new ResponseEntity<List<HrRequest>>(requestService.getListOfHr(), HttpStatus.OK);
	}
	@PreAuthorize("hasRole('HR')")
	@PutMapping("/update/{hrId}")
	public ResponseEntity<HrRequest> updateHrRequestById(@PathVariable int hrId,@RequestBody HrRequest updateEmployeeRequestDetail) {
		return new ResponseEntity<HrRequest>(requestService.updateHrRequestById(hrId, updateEmployeeRequestDetail), HttpStatus.OK);
	}
	@PreAuthorize("hasRole('HR')")
	@DeleteMapping("/delete/{hrId}")
	public ResponseEntity<HrRequest> deleteHrByIdController(@PathVariable int hrId) {
		return new ResponseEntity<HrRequest>(requestService.deleteHrById(hrId), HttpStatus.OK);
	}
	@PreAuthorize("hasRole('HR')")
	@GetMapping("/pagesets")
	public ResponseEntity<Page<HrRequest>> paginationFeature(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "2") int size) {
		Pageable pageable = PageRequest.of(page, size);
		return new ResponseEntity<Page<HrRequest>>(requestService.paginationFeature(pageable), HttpStatus.OK);
	}
	@PreAuthorize("hasRole('HR')")
	@GetMapping("sortByName")
	public ResponseEntity<List<HrRequest>> sortingHrRequestByNameController() {
		return new ResponseEntity<List<HrRequest>>(requestService.sortingHrRequestByName(),HttpStatus.OK);
	}
	@PreAuthorize("hasRole('HR')")
	@GetMapping("/sortByDateOfJoining")
	public ResponseEntity<List<HrRequest>> sortingHrRequestByJoiningOfDateController() {
		return new ResponseEntity<List<HrRequest>>(requestService.sortingHrRequestByJoiningOfDate(), HttpStatus.OK);
	}
	@PreAuthorize("hasRole('HR')")
	@PostMapping("/saveEmployee")
	public ResponseEntity<EmployeeRequest> saveEmployeeByHrLogin(@RequestBody EmployeeRequest employeeRequest) {
		return new ResponseEntity<EmployeeRequest>(requestService.saveEmployeeByHRLogin(employeeRequest), HttpStatus.OK);
	}
	@PreAuthorize("hasRole('HR')")
	@GetMapping("/getAllemployee")
	public ResponseEntity<List<EmployeeRequest>> getListOfEmployeeController() {
	    return new ResponseEntity<>(requestService.getListOfEmployeeByHrId(), HttpStatus.OK);
	}

	@PreAuthorize("hasRole('HR')")
	@PutMapping("/updates/{id}")
	public ResponseEntity<EmployeeRequest> updateEmployeeRequestByIdController(int id, EmployeeRequest updateEmployeeRequestDetail) {
	    return new ResponseEntity<>(requestService.updateEmployeeRequestById(id, updateEmployeeRequestDetail), HttpStatus.OK);
	}

	@PreAuthorize("hasRole('HR')")
	@DeleteMapping("/deletes/{id}")
	public ResponseEntity<EmployeeRequest> deleteEmployeeByIdController(@PathVariable int id) {
	    return new ResponseEntity<>(requestService.deleteEmployeeById(id), HttpStatus.OK);
	}

	@PreAuthorize("hasRole('HR')")
	@GetMapping("/pageSet")
	public ResponseEntity<Page<EmployeeRequest>> paginationFeatureEmployee(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "2") int size) {
	    Pageable pageable = PageRequest.of(page, size);
	    return new ResponseEntity<>(requestService.paginationFeatureEmployee(pageable), HttpStatus.OK);
	}

	@PreAuthorize("hasRole('HR')")
	@GetMapping("/sorted")
	public ResponseEntity<List<EmployeeRequest>> sortingEmployeeByNameController() {
	    return new ResponseEntity<>(requestService.sortingEmployeeRequestByName(), HttpStatus.OK);
	}

	@PreAuthorize("hasRole('HR')")
	@GetMapping("/sortingByDateOfJoining")
	public ResponseEntity<List<EmployeeRequest>> sortingEMployeeRequestByJoiningOfDateController() {
		return new ResponseEntity<>(requestService.sortingEMployeeRequestByJoiningOfDate(),HttpStatus.OK);
	}
}
