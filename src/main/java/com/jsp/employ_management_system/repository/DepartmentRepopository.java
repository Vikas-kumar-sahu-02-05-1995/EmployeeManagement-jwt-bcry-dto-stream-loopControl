package com.jsp.employ_management_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jsp.employ_management_system.entity.Department;

@Repository
public interface DepartmentRepopository extends JpaRepository<Department, Long>{




}
