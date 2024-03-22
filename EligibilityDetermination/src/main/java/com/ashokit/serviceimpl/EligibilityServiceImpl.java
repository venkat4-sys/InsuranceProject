package com.ashokit.serviceimpl;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ashokit.entity.CitizenEntity;
import com.ashokit.entity.EducationEntity;
import com.ashokit.entity.EligibleDetails;
import com.ashokit.entity.IncomeEntity;
import com.ashokit.entity.KidEntity;
import com.ashokit.entity.PlanSelectionEntity;
import com.ashokit.entity.PlansEntity;
import com.ashokit.repo.Citizenrepo;
import com.ashokit.repo.EducationRepo;
import com.ashokit.repo.EligibleRepo;
import com.ashokit.repo.IncomeRepo;
import com.ashokit.repo.KidsRepo;
import com.ashokit.repo.PlanselectionRepo;
import com.ashokit.service.EligibilityService;
import com.ashokit.utility.AgeCalculator;

@Service
public class EligibilityServiceImpl implements EligibilityService {

	@Autowired
	private Citizenrepo citzenrepo;
	@Autowired
	private EducationRepo edurepo;
	@Autowired
	private IncomeRepo incomerepo;

	@Autowired
	private KidsRepo kidrepo;

	@Autowired
	private AgeCalculator agecalculator;

	@Autowired
	private PlanselectionRepo planselerepo;

	@Autowired
	private EligibleRepo eligiblerepo;

	public boolean checkEligibility(Integer caseNumber) {

		CitizenEntity citizenEntity = citzenrepo.findByCaseNumber(caseNumber);

		List<KidEntity> kidEntity = kidrepo.findByCitizen_CaseNumber(caseNumber);

		EducationEntity EducationEntity = edurepo.findByCitizen_CaseNumber(caseNumber);

		IncomeEntity incomeEntity = incomerepo.findByCitizen_CaseNumber(caseNumber);
		Double totalIncome = 0.0;
		String status = "";
		if (incomeEntity != null || citizenEntity != null || EducationEntity != null || kidEntity != null) {

			if (incomeEntity != null) {

				totalIncome = incomeEntity.getMonthSal() + incomeEntity.getPropertyIncome()
						+ incomeEntity.getRentIncome();

			}

			PlanSelectionEntity plans = planselerepo.findByCaseNumber(caseNumber);
			PlansEntity planentity = plans.getPlan();
			Integer planId = planentity.getPlanId();
			// Assigning the benfitAmounts to the plans
			List<Integer> asList = Arrays.asList(230, 300, 170, 250, 400, 240);
			Integer benfitAmt = asList.get(planId - 2);

			int citizenAge = agecalculator.getAge(citizenEntity.getDob()); // citzen age
			List<Integer> kidAges = new ArrayList<Integer>(); // for storing kid ages //15,17

			boolean ageCheck = false;
			if (kidEntity != null && ! kidEntity.isEmpty()) {
				for (KidEntity kentity : kidEntity) {

					kidAges.add(agecalculator.getAge(kentity.getKidDob()));

				}
				for (int i = 0; i < kidAges.size(); i++) {

					ageCheck = agecalculator.ageCheck(kidAges);

					if (totalIncome < 500 && ageCheck) {

						status = "Approved";

						PlanSelectionEntity planselectEntity = planselerepo.findByCaseNumber(caseNumber);

						PlansEntity plan = planselectEntity.getPlan();

						EligibleDetails edentity = new EligibleDetails();

						edentity.setPlanStatus(status);
						edentity.setPlanName(plan.getPlanName());
						edentity.setBenfitAmount(benfitAmt);
						edentity.setDenialReason("N/A");

						CitizenEntity citizen = new CitizenEntity();
						citizen.setCaseNumber(citizenEntity.getCaseNumber());

						edentity.setCitizen(citizen);

						edentity.setEligibleStartDate(LocalDate.now().toString());

						LocalDate startDate = LocalDate.parse(LocalDate.now().toString()); // Replace with your actual
																							// start
																							// date
						long duration = 180; // Replace with your actual duration

						LocalDate endDate = startDate.plus(duration, ChronoUnit.DAYS);
						edentity.setEligibleEndDate(endDate.toString());

						eligiblerepo.save(edentity);

					}

					status = "Denied";

					PlanSelectionEntity planselectEntity = planselerepo.findByCaseNumber(caseNumber);

					PlansEntity plan = planselectEntity.getPlan();

					EligibleDetails edentity = new EligibleDetails();

					edentity.setPlanStatus(status);
					edentity.setPlanName(plan.getPlanName());
					edentity.setBenfitAmount(0);
					edentity.setDenialReason("N/A");

					CitizenEntity citizen = new CitizenEntity();
					citizen.setCaseNumber(citizenEntity.getCaseNumber());

					edentity.setCitizen(citizen);

					edentity.setEligibleStartDate("N/A");
					edentity.setEligibleEndDate("N/A");

					eligiblerepo.save(edentity);

				}

				return false;
			}

			if (planentity.getPlanName().equals("RIW") && totalIncome == 0
					&& !EducationEntity.getHighestDegree().isEmpty()
					|| planentity.getPlanName().equals("SNAP") && totalIncome < 300
					|| planentity.getPlanName().equals("MediCaid") && totalIncome < 320
					|| planentity.getPlanName().equals("MediCare") && citizenAge < 65) {
				status = "Approved";

				PlanSelectionEntity planselectEntity = planselerepo.findByCaseNumber(caseNumber);

				PlansEntity plan = planselectEntity.getPlan();

				EligibleDetails edentity = new EligibleDetails();

				edentity.setPlanStatus(status);
				edentity.setPlanName(plan.getPlanName());
				edentity.setBenfitAmount(benfitAmt);
				edentity.setDenialReason("N/A");

				CitizenEntity citizen = new CitizenEntity();
				citizen.setCaseNumber(citizenEntity.getCaseNumber());

				edentity.setCitizen(citizen);

				edentity.setEligibleStartDate(LocalDate.now().toString());

				LocalDate startDate = LocalDate.parse(LocalDate.now().toString()); // Replace with your actual start
																					// date
				long duration = 180; // Replace with your actual duration

				LocalDate endDate = startDate.plus(duration, ChronoUnit.DAYS);
				edentity.setEligibleEndDate(endDate.toString());

				eligiblerepo.save(edentity);

				return true;

			}

			status = "Denied";

			PlanSelectionEntity planselectEntity = planselerepo.findByCaseNumber(caseNumber);

			PlansEntity plan = planselectEntity.getPlan();

			EligibleDetails edentity = new EligibleDetails();

			edentity.setPlanStatus(status);
			edentity.setPlanName(plan.getPlanName());
			edentity.setBenfitAmount(0);
			edentity.setDenialReason("N/A");

			CitizenEntity citizen = new CitizenEntity();
			citizen.setCaseNumber(citizenEntity.getCaseNumber());

			edentity.setCitizen(citizen);

			edentity.setEligibleStartDate("N/A");
			edentity.setEligibleEndDate("N/A");

			eligiblerepo.save(edentity);

		}

		return false;
	}

}
