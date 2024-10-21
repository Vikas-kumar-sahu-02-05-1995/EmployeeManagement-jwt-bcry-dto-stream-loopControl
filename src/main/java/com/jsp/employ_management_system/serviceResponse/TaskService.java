package com.jsp.employ_management_system.serviceResponse;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jsp.employ_management_system.dto.TaskDto;
import com.jsp.employ_management_system.entity.Employee;
import com.jsp.employ_management_system.entity.Task;
import com.jsp.employ_management_system.repository.EmployeeRepository;
import com.jsp.employ_management_system.repository.TaskRepo;

@Service
public class TaskService {

    @Autowired
    private TaskRepo taskRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    public Task addTask(TaskDto taskRequest) {
    	  if (taskRequest.getDueDate().isBefore(LocalDateTime.now())) {
    	        throw new IllegalArgumentException("Due date cannot be in the past");
    	    }
    	Task task = new Task();
        task.setTitle(taskRequest.getTitle());
        task.setDescription(taskRequest.getDescription());
        task.setCreateDate(LocalDateTime.now());
        task.setDueDate(taskRequest.getDueDate());

        Employee employee = employeeRepository.findById(taskRequest.getEmpId())
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        task.setEmployee(employee);
        return taskRepository.save(task);
    }
}

