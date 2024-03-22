package com.ashokit.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "citizen_entity")
public class CitizenEntity {

	@Id
	private Integer caseNumber;

	private String fullname;

	private String email;

	private String mobNumber;

	private String gender;

	private String dob;

	private String ssn;

	private String city;

	private String state;

	private String houseNum;

	@ManyToOne
	@JoinColumn(name = "userId")
	private User user;

}
