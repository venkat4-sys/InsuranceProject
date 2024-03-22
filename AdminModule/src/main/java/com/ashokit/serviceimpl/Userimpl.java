package com.ashokit.serviceimpl;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ashokit.binding.Caseworker;
import com.ashokit.binding.LoginBinding;
import com.ashokit.binding.Plans;
import com.ashokit.binding.Unlock;
import com.ashokit.constants.AppConstants;
import com.ashokit.entity.PlansEntity;
import com.ashokit.entity.User;
import com.ashokit.entity.UserRoleEntity;
import com.ashokit.repo.PlanRepo;
import com.ashokit.repo.UserRepo;
import com.ashokit.service.UserActivities;
import com.ashokit.utility.EmailUtitlity;
import com.ashokit.utility.Passwordutils;

@Service
public class Userimpl implements UserActivities {

	@Autowired
	private UserRepo userrepo;

	@Autowired
	private PlanRepo planrepo;

	@Autowired
	private EmailUtitlity emailutil;

	@Autowired
	private HttpSession session;

	@Autowired
	public Passwordutils pwd;

	public String login(LoginBinding login) {
		User entity = userrepo.findByEmailAndPassword(login.getEmail(), login.getPassword());

		if (entity == null) {

			return AppConstants.LOGIN_FAIL;
		}

		// create the session for admin and case worker
		session.setAttribute("userId", entity.getUserId());

		return AppConstants.LOGIN_SUCCESS;
	}

	public String forgotPwd(String email) {

		User entity = userrepo.findByEmail(email);
		if (entity == null) {
			return AppConstants.EMAIL_FAIL;

		}

		String subject = "Recover Password";
		String body = "your pwd:" + entity.getPassword();

		emailutil.sendEmail(subject, body, email);

		return AppConstants.EMAIL_SUCCESS;

	}

	public String saveWorker(Caseworker details) {
		

		if (details.getUserId() != null) {//

			User user = new User();
			user.setStatus("Unlocked");
			
			UserRoleEntity role = new UserRoleEntity();
			role.setRoleId(2);

			user.setUserrole(role);
			
			
		   

			BeanUtils.copyProperties(details, user);

			userrepo.save(user);

			return AppConstants.WORK_UPDATE ;

		}
      
		/*
		 * above code related to update uperation
		 * 
		 */
		
		
		User user = new User();

		BeanUtils.copyProperties(details, user);
		user.setStatus("Locked");

		String generatePassword = pwd.generatePassword();

		user.setPassword(generatePassword);

		UserRoleEntity role = new UserRoleEntity();
		role.setRoleId(2);

		user.setUserrole(role);

		userrepo.save(user);

		String subject = "Welcome to IES Application";

		String to = user.getEmail();

		String body = "<h1> Please click on below link to unlock your account</h1>"
				+ "<a href=\"http://localhost:8080/unlock?staffEmail=" + to + "\">Unlock your account</a>"
				+ "<p>Your temporary password is:<strong>" + generatePassword + "</string></p>";

		emailutil.sendEmail(subject, body, to);

		return AppConstants.WORK_SAVE;
	}

	public String unlock(String email, Unlock unlock) {

		User entity = userrepo.findByEmail(email);

		if (unlock.getPwd().equals(entity.getPassword())) {

			entity.setStatus("Unlocked");

			entity.setPassword(unlock.getNewpwd());

			userrepo.save(entity);

			return AppConstants.USER_UNLOCK;

		}

		return AppConstants.USER_UNLOCK_FAIL;
	}

	public String savePlan(Plans plan) {

		if (plan.getPlanId() != null) {

			PlansEntity plans = new PlansEntity();

			BeanUtils.copyProperties(plan, plans);

			planrepo.save(plans);

			return AppConstants.UPDATE_PLAN;

		}

		PlansEntity plans = new PlansEntity();

		BeanUtils.copyProperties(plan, plans);

		planrepo.save(plans);

		return AppConstants.SAVE_PLAN;
	}

	public List<PlansEntity> listOfPlans() {

		

		return planrepo.getAllPlans();
	}
	
	public void updatePlanStatus(Integer planId,boolean status) {
		Optional<PlansEntity> findById = planrepo.findById(planId);
		if(findById.isPresent()) {
			
			PlansEntity plansEntity = findById.get();
			plansEntity.setActive(status);
			planrepo.save(plansEntity);
			
			
		}
		
	}
	public void updateUserStatus(Integer userId,boolean status) {
         Optional<User> findById = userrepo.findById(userId);
         
         if(findById.isPresent()) {
 			
 			 User user = findById.get();
 			user.setActive(status);
 			userrepo.save(user);
 			
 			
 		}
    }
	
	

}
