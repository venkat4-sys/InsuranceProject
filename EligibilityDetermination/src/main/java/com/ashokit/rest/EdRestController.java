package com.ashokit.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ashokit.service.EligibilityService;

@RestController
public class EdRestController {
	
	@Autowired
	private EligibilityService eligibleservice;
	
	@PostMapping("/check")
	public void check (@RequestParam Integer caseNumber) {
		
		eligibleservice.checkEligibility(caseNumber);
		
		
	}


	
}
