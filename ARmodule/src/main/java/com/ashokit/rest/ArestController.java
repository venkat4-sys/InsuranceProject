package com.ashokit.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ashokit.binding.CitizenBinding;
import com.ashokit.service.Citizenservice;

@RestController
public class ArestController {
	@Autowired
	private Citizenservice citizenservice;
	
	@PostMapping("/savecitizen")
	public String saveCitizen(@RequestBody CitizenBinding citizen) {
		
		return citizenservice.saveCitizen(citizen);
	}
	
	
}
