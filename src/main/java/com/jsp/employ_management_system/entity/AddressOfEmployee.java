package com.jsp.employ_management_system.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Setter;

@Entity
@Setter
public class AddressOfEmployee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int addressId;
	private String addressdetail;
	
	
	public int getAddressId() {
		return addressId;
	}


	public String getAddressdetail() {
		return addressdetail;
	}


	@ManyToOne
	private Employee employee;

}