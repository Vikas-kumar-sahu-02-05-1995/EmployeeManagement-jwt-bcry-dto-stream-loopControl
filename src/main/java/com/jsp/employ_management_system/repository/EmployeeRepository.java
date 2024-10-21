package com.jsp.employ_management_system.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.jsp.employ_management_system.dto.EmployeeRequest;
import com.jsp.employ_management_system.entity.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

	@Query("select e from Employee e where e.email = : email")
	public Employee getEmployeeByEmployeeName(@Param("email") String email);

	Page<Employee> findAll(final Pageable pageable);

	
	EmployeeRequest save(EmployeeRequest employeeRequest);

	public Employee findByEmail(String email);
//	List<Employee> findByHrId(int hrId);
}
