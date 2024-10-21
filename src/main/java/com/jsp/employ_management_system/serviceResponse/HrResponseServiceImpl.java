package com.jsp.employ_management_system.serviceResponse;

import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jsp.employ_management_system.dto.DepartmentRequest;
import com.jsp.employ_management_system.dto.EmployeeRequest;
import com.jsp.employ_management_system.dto.HrRequest;
import com.jsp.employ_management_system.entity.AddressHr;
import com.jsp.employ_management_system.entity.AddressOfEmployee;
import com.jsp.employ_management_system.entity.Department;
import com.jsp.employ_management_system.entity.Employee;
import com.jsp.employ_management_system.entity.HR;
import com.jsp.employ_management_system.repository.AddresHrRepo;
import com.jsp.employ_management_system.repository.DepartmentRepopository;
import com.jsp.employ_management_system.repository.EmployeeRepository;
import com.jsp.employ_management_system.repository.HrRepository;
import com.jsp.employ_management_system.service.HrRequestService;

@Service
@Transactional
public class HrResponseServiceImpl  implements HrRequestService {

//	@Autowired
//	private RoleRepo repoRole;
	
	@Autowired
	private ModelMapper mapper;
	@Autowired
	private HrRepository hrRepository;

	private BCryptPasswordEncoder encoder1 = new BCryptPasswordEncoder(12) ;
	@Autowired
	private DepartmentServiceImpl departmentServiceImpl;
	@Autowired
	private EmployeeRepository employeeRepository;
	@Autowired
	private AddresHrRepo addresHrRepo;
	@Autowired
	private DepartmentRepopository departmentRepopository;
	
	@Override
	public HrRequest loginServiceservice( HrRequest hrRequest ) {
		
		return hrRequest;
	}
	
	@Override
	public HrRequest saveHrRequest(HrRequest hrRequest) {
		HR hrEntity = convertToEntity(hrRequest);
		System.out.println(hrRequest);
		
		hrEntity.setPasswords(encoder1.encode(hrEntity.getPasswords()));
		HR savedhr = hrRepository.save(hrEntity);
		return convertToResponse(savedhr);
	}
	
	private HR getLoggedInHR() {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
			return 	hrRepository.findByEmail(username);
	}
	
	private HrRequest mapToHrResponse(HR hrreq) {
		return new HrRequest();
	}
	public HrRequest getLoggedInHrDetails() {
		HR loggedInHr = getLoggedInHR();
		return mapToHrResponse(loggedInHr);
	}
	
	@Override
	public DepartmentRequest saveDepartmentByHrLogin(DepartmentRequest hrreq) {
		HR saveddepartment =getLoggedInHR();
		Department department = mapper.map(saveddepartment,Department.class);
		department.setDepartmentName(hrreq.getDepartmentName());
		Department savedDepartment = departmentRepopository.save(department);

		return mapper.map(savedDepartment, DepartmentRequest.class);
	}

	@Override
	public List<HrRequest> getListOfHr() {
		List<HR> hrs = hrRepository.findAll();
		return hrs
				.stream()
				.map(this::convertToResponse)
				.collect(Collectors.toList());
	}

	@Override
	public HrRequest updateHrRequestById(int hrId, HrRequest updateEmployeeRequestDetail) {
		return hrRepository.findById(hrId).map(hrreq -> {
			hrreq.setAge(updateEmployeeRequestDetail.getAge());
			hrreq.setQualification(updateEmployeeRequestDetail.getQualification());
			hrreq.setName(updateEmployeeRequestDetail.getName());
			HR hr = hrRepository.save(hrreq);
			return convertToResponse(hr);
			})
				.orElseThrow(() -> new NoSuchElementException("no such id found "+hrId));
	}

	@Override
	public HrRequest deleteHrById(int hrId) {
		return hrRepository.findById(hrId)
				.map(hr -> {
					hrRepository.deleteById(hrId);
					return convertToResponse(hr);
				})
				.orElseThrow(() -> new NoSuchElementException("no such id found "+hrId));
	}

	@Override
	public Page<HrRequest> paginationFeature(Pageable pageable) {
		return hrRepository.findAll(pageable)
				.map(this::convertToResponse);
	}

	@Override
	public List<HrRequest> sortingHrRequestByName() {
		return hrRepository.findAll()
				.stream()
				.sorted((s1, s2) -> s1.getName().compareToIgnoreCase(s2.getName()))
				.map(this::convertToResponse)
				.collect(Collectors.toList());
	}

	@Override
	public List<HrRequest> sortingHrRequestByJoiningOfDate() {
		return hrRepository.findAll()
				.stream()
				.sorted(Comparator.comparing(HR::getDateOfJoining))
				.map(this::convertToResponse)
				.collect(Collectors.toList());
	}



	public HR convertToEntity(HrRequest hrRequest) {
		HR hr = this.mapper.map(hrRequest, HR.class );
		if(hr.getAddressesHr() !=null) {
			for(AddressHr addressHr : hr.getAddressesHr()) {
				addressHr.setHr4(hr);
				System.out.println("Mapped address: "+addressHr );
			}
		}else {
			System.out.println("No Address found in hr request");
		}
		return hr;
	}
	
	public HrRequest convertToResponse(HR hr) {
		HrRequest hrRequest = this.mapper.map(hr, HrRequest.class);
		return hrRequest;
	}
	
    @Override
	public EmployeeRequest saveEmployeeByHRLogin(EmployeeRequest employeeRequest ) {
	    HR savedemployee = getLoggedInHR();
	  Employee employee = mapper.map(savedemployee, Employee.class);
	       employee.setPasswords(encoder1.encode(employeeRequest.getPasswords()));
	       employee.setDateOfJoining(employeeRequest.getDateOfJoining());
	       employee.setEmail(employeeRequest.getEmail());
	       employee.setName(employeeRequest.getName());
	       employee.setSalary(employeeRequest.getSalary());
	       employee.setAddressOfEmployees(employeeRequest.getAddressOfEmployees());
	       employee.setDepartment2(employeeRequest.getDepartment2());
	       employee.setHr1(savedemployee);
	     if(employee.getAddressOfEmployees() != null) {
	    	 employee.getAddressOfEmployees().forEach(address -> address.setEmployee(employee)); 
	     }
	 
	  Employee savedemployee2 = employeeRepository.save(employee);
	  return mapper.map(savedemployee2, EmployeeRequest.class);
    }
    
    
    
	public List<EmployeeRequest> getListOfEmployeeByHrId() {
		List<Employee> employees = employeeRepository.findAll();
		return employees
				.stream()
				.map(this::mapToEmployeeRequest)
				.collect(Collectors.toList());
	}

	public EmployeeRequest updateEmployeeRequestById(int id, EmployeeRequest updateEmployeeRequestDetail) {
		return employeeRepository.findById(id)
				.map(employe -> {
					employe.setDateOfJoining(updateEmployeeRequestDetail.getDateOfJoining());
					employe.setName(updateEmployeeRequestDetail.getName());
					employe.setSalary(updateEmployeeRequestDetail.getSalary());
					Employee updateEmployee = employeeRepository.save(employe);
					return mapToEmployeeRequest(updateEmployee);
				})
				.orElseThrow(() -> new NoSuchElementException("no such is found "));
	}

	public EmployeeRequest deleteEmployeeById(int id) {
		return employeeRepository.findById(id)
				.map(employee -> {
					employeeRepository.deleteById(id);
					return mapToEmployeeRequest(employee);
				})
				.orElseThrow(() -> new NoSuchElementException("no id found "+id));
	}

	public Page<EmployeeRequest> paginationFeatureEmployee(Pageable e1) {
		return employeeRepository.findAll(e1)
				.map(this::mapToEmployeeRequest);
	}

	public List<EmployeeRequest> sortingEmployeeRequestByName() {
		return employeeRepository.findAll()
				.stream()
				.sorted((e1, e2) -> e1.getName().compareToIgnoreCase(e2.getName()))
//				.sorted(Comparator.comparing(Employee::getName, String.CASE_INSENSITIVE_ORDER))
				.map(this::mapToEmployeeRequest)
				.collect(Collectors.toList());
	}
	

	@Override
	public List<EmployeeRequest> sortingEMployeeRequestByJoiningOfDate() {
		return employeeRepository.findAll()
				.stream()
				.sorted(Comparator.comparing(Employee::getDateOfJoining))
				.map(this::mapToEmployeeRequest)
				.collect(Collectors.toList());
	}
    
    public EmployeeRequest mapToEmployeeRequest(Employee employee) {
		EmployeeRequest request = this.mapper.map(employee, EmployeeRequest.class);

	    return request;
	}
	
	public Employee convertToEntityEmployee(EmployeeRequest employeeRequest) {
	    Employee employee = this.mapper.map(employeeRequest, Employee.class);
	    
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

	public DepartmentRequest convertToResponseDepartment(Department department) {
		DepartmentRequest request = this.mapper.map(department, DepartmentRequest.class) ;
		return request;
	}

	public Department convertToEntityDepartment(DepartmentRequest departmentRequest) {
		Department department = this.mapper.map(departmentRequest, Department.class);

		return department;
	}

	
}
