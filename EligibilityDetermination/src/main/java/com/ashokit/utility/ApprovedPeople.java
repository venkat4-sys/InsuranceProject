package com.ashokit.utility;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ashokit.entity.CitizenEntity;
import com.ashokit.entity.EligibleDetails;
import com.ashokit.entity.PlanSelectionEntity;
import com.ashokit.entity.PlansEntity;
import com.ashokit.repo.EligibleRepo;
import com.ashokit.repo.PlanselectionRepo;

public class ApprovedPeople {

	@Autowired
	private PlanselectionRepo planselerepo;

	@Autowired
	private EligibleRepo eligiblerepo;

	public void approved(Integer caseNumber) {

		PlanSelectionEntity plans = planselerepo.findByCaseNumber(caseNumber);
		PlansEntity planentity = plans.getPlan();
		Integer planId = planentity.getPlanId();

		List<Integer> asList = Arrays.asList(230, 300, 170, 250, 400, 240);
		Integer benfitAmt = asList.get(planId - 2);

		String status = "Approved";

		PlanSelectionEntity planselectEntity = planselerepo.findByCaseNumber(caseNumber);

		PlansEntity plan = planselectEntity.getPlan();

		EligibleDetails edentity = new EligibleDetails();

		edentity.setPlanStatus(status);
		edentity.setPlanName(plan.getPlanName());
		edentity.setBenfitAmount(benfitAmt);
		edentity.setDenialReason("N/A");

		CitizenEntity citizen = new CitizenEntity();
		//citizen.setCaseNumber(citizenEntity.getCaseNumber());

		edentity.setCitizen(citizen);

		edentity.setEligibleStartDate(LocalDate.now().toString());

		LocalDate startDate = LocalDate.parse(LocalDate.now().toString()); // Replace with your actual start
																			// date
		long duration = 180; // Replace with your actual duration

		LocalDate endDate = startDate.plus(duration, ChronoUnit.DAYS);
		edentity.setEligibleEndDate(endDate.toString());

		eligiblerepo.save(edentity);

	}

}
