package com.jsp.employ_management_system.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Setter;

@Entity
@Setter
public class AddressHr {

	public int getAddressHrId() {
		return addressHrId;
	}

	public String getHrAddressdetail() {
		return hrAddressdetail;
	}

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private int addressHrId;
	private String hrAddressdetail;
	
	@ManyToOne
	private HR hr4;
}
