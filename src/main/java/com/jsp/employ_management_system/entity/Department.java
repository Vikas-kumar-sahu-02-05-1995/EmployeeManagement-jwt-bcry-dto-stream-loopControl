package com.jsp.employ_management_system.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
public class Department {

	public Long getId() {
		return id;
	}

	public String getDepartmentName() {
		return departmentName;
	}


	@Id
	@SequenceGenerator(sequenceName = "seq_id", name = "ids", initialValue = 100, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ids")
	private Long id;
	private String departmentName;
	
	
	@OneToMany(mappedBy = "department2")
	private List<Employee> employees;

	@ManyToOne(cascade = CascadeType.ALL)
	private HR hr;
}
