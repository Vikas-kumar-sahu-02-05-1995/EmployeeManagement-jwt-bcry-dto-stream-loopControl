package com.jsp.employ_management_system.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import lombok.Setter;

@Entity
@Setter
public class Employee {

    @Id
    @SequenceGenerator(sequenceName = "seq_id", name = "ids", initialValue = 1000, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ids")
    private int empId;

    private String name;
    private Date dateOfJoining;
    private long salary;
    private String email;
    private String passwords;
    private Long managerId;
    
    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    private List<Attendance> attendances =new ArrayList<>();
    
    @ManyToOne
    private HR hr1;
    
    @OneToMany(mappedBy = "employee", cascade  = CascadeType.ALL)
    private List<AddressOfEmployee> addressOfEmployees = new ArrayList<>();
    
    @ManyToOne
    private Department department2;

    @OneToMany(mappedBy = "employee")
    private List<Task> tasks;

	public int getEmpId() {
		return empId;
	}
	public Long getManagerId() {
		return managerId;
	}

	public String getName() {
		return name;
	}

	public Date getDateOfJoining() {
		return dateOfJoining;
	}

	public long getSalary() {
		return salary;
	}

	public String getEmail() {
		return email;
	}

	public String getPasswords() {
		return passwords;
	}
	
	public List<AddressOfEmployee> getAddressOfEmployees() {
		return addressOfEmployees;
	}

	public List<Attendance> getAttendances() {
		return attendances;
	}

	public Department getDepartment2() {
		return department2;
	}

    
}
