package com.jsp.employ_management_system.responseController;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.employ_management_system.dto.TaskDto;
import com.jsp.employ_management_system.entity.Task;
import com.jsp.employ_management_system.serviceResponse.TaskService;

@RestController
@RequestMapping("/tasks")
public class TaskController {

	@Autowired
	private TaskService taskService;
	
	@PreAuthorize("hasRole('MANAGER')")
    @PostMapping("/add")
    public ResponseEntity<Task> addTask(@RequestBody TaskDto taskRequest) {
        Task createdTask = taskService.addTask(taskRequest);
        return ResponseEntity.ok(createdTask);
    }
}

