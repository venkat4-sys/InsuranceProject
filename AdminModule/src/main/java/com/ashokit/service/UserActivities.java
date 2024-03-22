package com.ashokit.service;

import java.util.List;

import com.ashokit.binding.Caseworker;
import com.ashokit.binding.LoginBinding;
import com.ashokit.binding.Plans;
import com.ashokit.binding.Unlock;
import com.ashokit.entity.PlansEntity;

public interface UserActivities {
	
	
	//login purpose
	public String login(LoginBinding login);
	
	//recover the pwd
	public String forgotPwd(String email);
	
	public String saveWorker(Caseworker details);
	
	public String unlock(String email,Unlock unlock);
	
	public String savePlan(Plans plan);
	
	public List<PlansEntity> listOfPlans();
	
	public void updatePlanStatus(Integer planId,boolean status);
	
	public void updateUserStatus(Integer userId,boolean status);

}
