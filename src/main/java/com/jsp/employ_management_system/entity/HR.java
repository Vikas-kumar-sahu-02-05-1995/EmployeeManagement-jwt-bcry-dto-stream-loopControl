package com.jsp.employ_management_system.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class HR  {

   @Id
    @SequenceGenerator(sequenceName = "seq_id", name = "ids", initialValue = 51, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ids")
    private int hrId;

    private String name;
    private String qualification;
    private Date dateOfJoining;
    private int age;
    private String experience;
    private String email;
    private String passwords;  // Changed to `password` for clarity and consistency

    @OneToMany(mappedBy = "hr4", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<AddressHr> addressesHr;


    @OneToMany(mappedBy = "hr1")
    private List<Employee> employees = new ArrayList<>();
    
    @OneToMany(mappedBy = "hr")
    private List<Department> department;

//    @ManyToMany(fetch =  FetchType.EAGER)
//    private Set<Role> roles;
}
