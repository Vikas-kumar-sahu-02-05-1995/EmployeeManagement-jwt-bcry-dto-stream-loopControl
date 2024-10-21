package com.jsp.employ_management_system.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.jsp.employ_management_system.entity.Employee;
import com.jsp.employ_management_system.entity.HR;
import com.jsp.employ_management_system.repository.EmployeeRepository;
import com.jsp.employ_management_system.repository.HrRepository;
import com.jsp.employ_management_system.repository.TaskRepo;

@Service
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	private TaskRepo taskRepo;
    @Autowired
    private HrRepository hrRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // Attempt to find the HR user by email
        HR hrUser = hrRepository.findByEmail(email);
        if (hrUser != null) {
            return new User(
                hrUser.getEmail(),
                hrUser.getPasswords(),
                List.of(new SimpleGrantedAuthority("ROLE_HR")) // Assign HR role
            );
        }

        // Attempt to find the Employee user by email
        Employee employee = employeeRepository.findByEmail(email);
        if (employee != null) {
            List<SimpleGrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority("ROLE_EMPLOYEE")); // Assign Employee role
            
            // Check if the employee has a managerId, assign ROLE_MANAGER if applicable
            if (employee.getManagerId() != null) {
            	
                authorities.add(new SimpleGrantedAuthority("ROLE_MANAGER"));
            }

            return new User(
                employee.getEmail(),
                employee.getPasswords(),
                authorities
            );
        }

        // If neither HR nor Employee is found, throw an exception
        throw new UsernameNotFoundException("User with email " + email + " not found");
    }
}