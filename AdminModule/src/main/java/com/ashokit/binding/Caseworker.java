package com.ashokit.binding;

import lombok.Data;

@Data
public class Caseworker {

	private Integer userId;

	private String username;

	private String email;

	private String gender;

	private String mobileNumber;

	private String dob;

	private Long ssnNumber;

	private String password;

	private Integer CreatedByfk;

	private Integer UpdatedByfk;
	
	 private boolean active; 

}
