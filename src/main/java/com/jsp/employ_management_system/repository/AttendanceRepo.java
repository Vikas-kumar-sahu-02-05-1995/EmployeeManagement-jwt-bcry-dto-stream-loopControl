package com.jsp.employ_management_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jsp.employ_management_system.entity.Attendance;

@Repository
public interface AttendanceRepo extends JpaRepository<Attendance, Long> {

	
}
