package com.ashokit.service;

import java.util.List;

import com.ashokit.binding.EducationBinding;
import com.ashokit.binding.IncomeBinding;
import com.ashokit.binding.KidsBinding;
import com.ashokit.binding.PlanAssignBinding;

public interface DcService {
	
	
	public String saveIncomeDtls(IncomeBinding income);
	
	public String planAssign(PlanAssignBinding planassign);
	
	public String educationDtls(EducationBinding planassign);
	
	
	public String kidsDtls(Integer caseNumber,List<KidsBinding> kids);
	
	
	

}
