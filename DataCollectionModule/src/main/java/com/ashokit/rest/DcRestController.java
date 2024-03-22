package com.ashokit.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ashokit.binding.EducationBinding;
import com.ashokit.binding.IncomeBinding;
import com.ashokit.binding.KidsBinding;
import com.ashokit.binding.PlanAssignBinding;
import com.ashokit.service.DcService;

@RestController
public class DcRestController {

	@Autowired
	private DcService dcservice;

	@PostMapping("/planassign")
	public String assignPlan(@RequestBody PlanAssignBinding planassign) {

		return dcservice.planAssign(planassign);

	}

	@PostMapping("/income")
	public String saveIncomeDtls(@RequestBody IncomeBinding income) {

		return dcservice.saveIncomeDtls(income);

	}

	@PostMapping("/education")
	public String saveEduDtls(@RequestBody EducationBinding education) {

		return dcservice.educationDtls(education);

	}

	@PostMapping("/children")
	public String saveKidsDtls(@RequestParam Integer caseNumber, @RequestBody List<KidsBinding> kids) {

		return dcservice.kidsDtls(caseNumber, kids);

	}

}
