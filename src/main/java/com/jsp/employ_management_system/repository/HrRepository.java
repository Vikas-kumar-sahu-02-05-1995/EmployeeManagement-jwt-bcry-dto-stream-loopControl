package com.jsp.employ_management_system.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.jsp.employ_management_system.entity.HR;

@Repository
public interface HrRepository extends JpaRepository<HR, Integer> {


	
	public HR findByEmail(String email);
	
	@Query("SELECT h FROM HR h ORDER BY h.dateOfJoining ASC")
    List<HR> findAllSortedByDateOfJoining();
	
	@Query("select h from HR h where h.email = : email")
	public HR getHrByHrName(@Param("email") String email);
}
