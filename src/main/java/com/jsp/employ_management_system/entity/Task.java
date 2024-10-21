package com.jsp.employ_management_system.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Setter;

@Entity
@Setter
public class Task {

	@Id
	private Long id;
	
	private String title;
	private String description;
	private LocalDateTime createDate;
	private LocalDateTime dueDate;
	private int empId;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private Employee employee;

	public Long getId() {
		return id;
	}

	public int empId() {
		return empId;
	}

	public String getDescription() {
		return description;
	}
	
	public String getTitle() {
		return title;
	}

	public LocalDateTime getCreateDate() {
		return createDate;
	}

	public LocalDateTime getDueDate() {
		return dueDate;
	}

}
