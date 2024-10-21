package com.jsp.employ_management_system.serviceResponse;


import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jsp.employ_management_system.dto.DepartmentRequest;
import com.jsp.employ_management_system.entity.Department;
import com.jsp.employ_management_system.entity.Employee;
import com.jsp.employ_management_system.repository.AddresHrRepo;
import com.jsp.employ_management_system.repository.AddressOfEmployeeRepository;
import com.jsp.employ_management_system.repository.DepartmentRepopository;
import com.jsp.employ_management_system.repository.EmployeeRepository;
import com.jsp.employ_management_system.repository.HrRepository;
import com.jsp.employ_management_system.service.DepartmentRequestService;

@Service
public class DepartmentServiceImpl implements DepartmentRequestService {

//	@Autowired
//	private ModelMapper modelMapper;
	@Autowired
	private final ModelMapper modelMapper;

    
    public DepartmentServiceImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }
	@Autowired
	private EmployeeRepository employeeRepository;
	@Autowired
	private DepartmentRepopository repopository;

    @Autowired
    private HrRepository hrRepository;

    @Autowired
    private AddresHrRepo addressHrRepository;

    @Autowired
    private AddressOfEmployeeRepository addressRepository;

	@Override
	public DepartmentRequest createDepartment(DepartmentRequest departmentDTO) {
		Department department = convertToEntity(departmentDTO);
		Department saveddepartment = repopository.save(department);
		return convertToResponse(saveddepartment);
	}
	
	@Override
	public DepartmentRequest getDepartmentById(Long id) {
		return repopository.findById(id)
				.map(this::convertToResponse)
				.orElseThrow(() -> new NoSuchElementException("no such id found"));
	}

	@Override
	public List<DepartmentRequest> getAllDepartments() {
		List<Department> departments =repopository.findAll();
		return departments
				.stream()
				.map(this::convertToResponse)
				.collect(Collectors.toList());
	}

	@Override
	public DepartmentRequest updateDepartment(Long id, DepartmentRequest updatedepartment) {
		return repopository.findById(id)
				.map(depart -> {
					depart.setDepartmentName(updatedepartment.getDepartmentName());
					Department department = repopository.save(depart);
					return convertToResponse(department);
				})
				.orElseThrow(() -> new NoSuchElementException("no such id found"));
	}

	@Override
	public DepartmentRequest deleteDepartmentById(Long id) {
		return repopository.findById(id)
				.map(departmen -> {
					repopository.deleteById(id);
					return convertToResponse(departmen);
				})
				.orElseThrow(() -> new NoSuchElementException("no id found "+id));
	}
	
	@Override
	public List<DepartmentRequest> sortByDepartmentName() {
		return repopository.findAll()
				.stream()
				.sorted(Comparator.comparing(Department::getDepartmentName))
				.map(this::convertToResponse)
				.collect(Collectors.toList());
	}
	
	public DepartmentRequest convertToResponse(Department department) {
		DepartmentRequest request = this.modelMapper.map(department, DepartmentRequest.class) ;
		return request;
	}

	public Department convertToEntity(DepartmentRequest departmentRequest) {
		Department department = this.modelMapper.map(departmentRequest, Department.class);
		
//		if(department.getEmployees() != null) {
//			for(Employee employee : department.getEmployees()) {
//				employee.setDepartment2(department);
//				System.out.println("Mapped address: "+employee);
//			}
//		}else {
//			System.out.println("No eemployee found in required department");
//		}
		return department;
	}

	
}
