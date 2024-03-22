package com.ashokit.rest;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.ashokit.binding.Caseworker;
import com.ashokit.binding.LoginBinding;
import com.ashokit.binding.Plans;
import com.ashokit.binding.Unlock;
import com.ashokit.entity.PlansEntity;
import com.ashokit.entity.User;
import com.ashokit.repo.PlanRepo;
import com.ashokit.repo.UserRepo;
import com.ashokit.service.UserActivities;

@RestController
public class AdminRestController {

	@Autowired
	private UserActivities activities;

	@Autowired

	private UserRepo userrepo;
	
	@Autowired
	private PlanRepo planrepo;
	
	

	@PostMapping("/login")
	public String login(@RequestBody LoginBinding login) {

		return activities.login(login);

	}

	@PostMapping("/forgot")
	public String forgot(@RequestParam String email) {

		return activities.forgotPwd(email);

	}

	@PostMapping("/unlock")
	public String unlock(@RequestParam String email, @RequestBody Unlock unlock) {

		return activities.unlock(email, unlock);
	}

	@PostMapping("/save")
	public String saveWorker(@RequestBody Caseworker details) {

		return activities.saveWorker(details);

	}

	@GetMapping("/allworkers")
	public List<User> getAllWorkers() {

		List<User> allAccounts = userrepo.getAllWorks();
		return allAccounts.stream().filter(e -> e.getUserrole().getRoleName().equals("User"))
				.collect(Collectors.toList());

	}

	@PostMapping("/saveplan")
	public String savePlan(@RequestBody Plans plan) {

		return activities.savePlan(plan);

	}

	@GetMapping("/allplans")
	public List<PlansEntity> listOfPlans() {

		return activities.listOfPlans();

	}

	@PostMapping("/users/{userId}/deactivate")
	public void deactivateUser(@PathVariable("userId") Integer userId) {
		activities.updateUserStatus(userId, false);
		// Return appropriate response, redirect to a success page, or update UI as
		// required
	}

	@PostMapping("/users/{userId}/activate")
	public void activateUser(@PathVariable("userId") Integer userId) {
		activities.updateUserStatus(userId, true);
		// Return appropriate response, redirect to a success page, or update UI as
		// required
	}

	@PostMapping("/plans/deactivate/{planId}")
	public void deactivatePlan(@PathVariable("planId") Integer planId) {

		activities.updatePlanStatus(planId, false);

	}

	@PostMapping("/plans/activate/{planId}")
	public void activatePlan(@PathVariable("planId") Integer planId) {

		activities.updatePlanStatus(planId, true);

	}
	
	@GetMapping("/plancount")
	public long planCount() {
		
	 return planrepo.count();
		
	}
	
	

   /* @GetMapping("/send-session")
    public ResponseEntity<String> sendSessionId() {
        String sessionId = "userId";
        
        HttpHeaders headers = new HttpHeaders();
        headers.set("Session-ID", sessionId);
        
        HttpEntity<String> entity = new HttpEntity<String>(headers);
        
        String secondApiUrl = "http://localhost:8082/endpoint";
        
        ResponseEntity<String> response = restTemplate.exchange(secondApiUrl, HttpMethod.GET, entity, String.class);
        
        return response;
    }
*/
}
