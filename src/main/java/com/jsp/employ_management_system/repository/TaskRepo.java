package com.jsp.employ_management_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.jsp.employ_management_system.entity.Task;

@Repository
public interface TaskRepo extends JpaRepository<Task, Long> {

//	List<Task> findTasksByEmployeeId()

	@Query("select t from Task t where t.email = : email" )
	public Task getTaskByTaskName(@Param("email") String email);

}
