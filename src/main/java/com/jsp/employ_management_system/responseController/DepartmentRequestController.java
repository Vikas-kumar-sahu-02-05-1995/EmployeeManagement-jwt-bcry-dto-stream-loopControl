package com.jsp.employ_management_system.responseController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jsp.employ_management_system.dto.DepartmentRequest;
import com.jsp.employ_management_system.service.DepartmentRequestService;

@Controller
@RequestMapping("/department")
public class DepartmentRequestController {
	
	@Autowired 
	private DepartmentRequestService service;
	
	@PostMapping("/insert")
	public ResponseEntity<DepartmentRequest> createDepartmentController(@RequestBody DepartmentRequest departmentDTO) {
		return new ResponseEntity<DepartmentRequest>(service.createDepartment(departmentDTO), HttpStatus.OK);
	}
	
	@GetMapping("/getById/{id}")
	public ResponseEntity<DepartmentRequest> getDepartmentById(@PathVariable Long id) {
		return new ResponseEntity<DepartmentRequest>(service.getDepartmentById(id), HttpStatus.OK);
	}
	
	@GetMapping("/getAll")
	public ResponseEntity<List<DepartmentRequest>> getAllDepartmentsController(){
		return new ResponseEntity<List<DepartmentRequest>>(service.getAllDepartments(), HttpStatus.OK);
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<DepartmentRequest> updateDepartmentController(@PathVariable Long id, DepartmentRequest updatedepartment) {
		return new ResponseEntity<DepartmentRequest>(service.updateDepartment(id, updatedepartment), HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteById/{id}")
	public ResponseEntity<DepartmentRequest> deleteDepartmentByIdCOntroller(@PathVariable Long id) {
		return new ResponseEntity<DepartmentRequest>(service.deleteDepartmentById(id), HttpStatus.OK);
	}
	
	@GetMapping("/sortByDepartmentName")
	public ResponseEntity<List<DepartmentRequest>> sortByDepartmentName() {
		return new ResponseEntity<List<DepartmentRequest>>(service.sortByDepartmentName(), HttpStatus.OK);
	}
}
