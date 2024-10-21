package com.jsp.employ_management_system.entity;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.Setter;

@Entity
@Setter
public class Attendance {

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

         public Long getId() {
		return id;
	}

	public LocalDate getAttendanceDate() {
		return attendanceDate;
	}

	public String getStatus() {
		return status;
	}

	public LocalTime getCheckInTime() {
		return checkInTime;
	}

	public LocalTime getCheckOutTime() {
		return checkOutTime;
	}

	public String getRemarks() {
		return remarks;
	}

		@Column(name = "attendance_date", nullable = false)
	    private LocalDate attendanceDate;

	    @Column(name = "status", nullable = false)
	    private String status; // "Present", "Absent", "Leave", etc.

	    private LocalTime checkInTime;  // Time in "HH:mm" format

	    private LocalTime checkOutTime; // Time in "HH:mm" format

	    private String remarks; // Any additional comments or notes
	    
	    @ManyToOne
	    private Employee employee;
	    
	    
}
