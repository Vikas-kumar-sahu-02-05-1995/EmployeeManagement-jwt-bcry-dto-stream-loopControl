package com.jsp.employ_management_system.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.jsp.employ_management_system.dto.DepartmentRequest;
import com.jsp.employ_management_system.dto.EmployeeRequest;
import com.jsp.employ_management_system.dto.HrRequest;
import com.jsp.employ_management_system.entity.HR;
@Service
public interface HrRequestService {
	
	public HrRequest loginServiceservice( HrRequest hrRequest );

	public EmployeeRequest saveEmployeeByHRLogin(EmployeeRequest employeeRequest) ;
    public HrRequest saveHrRequest(HrRequest hrRequest);
    public DepartmentRequest saveDepartmentByHrLogin(DepartmentRequest hrreq);
    public HrRequest getLoggedInHrDetails() ;
	public List<HrRequest> getListOfHr();
	
	public HrRequest updateHrRequestById(int hrId, HrRequest updateEmployeeRequestDetail);
	
	public HrRequest deleteHrById(int hrId);
	
	public Page<HrRequest> paginationFeature(Pageable pageable);
	
	public List<HrRequest> sortingHrRequestByName() ;
	
	public List<HrRequest> sortingHrRequestByJoiningOfDate();
	
    public List<EmployeeRequest> getListOfEmployeeByHrId();
	
	public EmployeeRequest updateEmployeeRequestById(int id, EmployeeRequest updateEmployeeRequestDetail);
	
	public EmployeeRequest deleteEmployeeById(int id);
	
	public Page<EmployeeRequest> paginationFeatureEmployee(Pageable pageable);
	
	public List<EmployeeRequest> sortingEmployeeRequestByName() ;
	
	
	public List<EmployeeRequest> sortingEMployeeRequestByJoiningOfDate();
	
	
}
