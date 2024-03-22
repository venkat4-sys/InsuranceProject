package com.ashokit.binding;

import lombok.Data;

@Data
public class Plans {
	private Integer planId;
	
    private String planName;
	
	private String planStartDate;
	
	private String planEndDate;
	
	private String planCategory;
	
	private Integer createdByfk;

	private Integer updatedByfk;
	
	private boolean active;

}
