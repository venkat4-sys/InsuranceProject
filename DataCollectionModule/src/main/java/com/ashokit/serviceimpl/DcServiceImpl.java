package com.ashokit.serviceimpl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ashokit.binding.EducationBinding;
import com.ashokit.binding.IncomeBinding;
import com.ashokit.binding.KidsBinding;
import com.ashokit.binding.PlanAssignBinding;
import com.ashokit.entity.CitizenEntity;
import com.ashokit.entity.EducationEntity;
import com.ashokit.entity.IncomeEntity;
import com.ashokit.entity.KidEntity;
import com.ashokit.entity.PlanSelectionEntity;
import com.ashokit.entity.PlansEntity;
import com.ashokit.repo.EducationRepo;
import com.ashokit.repo.IncomeRepo;
import com.ashokit.repo.KidsRepo;
import com.ashokit.repo.PlanRepo;
import com.ashokit.repo.PlanselectionRepo;
import com.ashokit.service.DcService;

@Service
public class DcServiceImpl implements DcService {

	@Autowired
	private IncomeRepo incomerepo;

	@Autowired
	private PlanRepo planrepo;

	@Autowired
	private PlanselectionRepo planselectrepo;
	@Autowired
	private EducationRepo edurepo;

	@Autowired
	private KidsRepo kidrepo;

	public String planAssign(PlanAssignBinding planassign) {

		PlansEntity planentity = planrepo.findByPlanName(planassign.getPlanName());

		PlanSelectionEntity entity = new PlanSelectionEntity();

		PlansEntity plansentity = new PlansEntity();

		plansentity.setPlanId(planentity.getPlanId());

		entity.setPlan(plansentity);

		BeanUtils.copyProperties(planassign, entity);

		planselectrepo.save(entity);

		return "plan assigned successfully";
	}

	public String saveIncomeDtls(IncomeBinding income) {

		IncomeEntity entity = new IncomeEntity();

		CitizenEntity en = new CitizenEntity();

		en.setCaseNumber(income.getCaseNumber());
		entity.setCitizen(en);

		BeanUtils.copyProperties(income, entity);

		incomerepo.save(entity);

		return "incomeDetails saved...";
	}

	public String educationDtls(EducationBinding planassign) {

		EducationEntity entity = new EducationEntity();

		CitizenEntity en = new CitizenEntity();

		en.setCaseNumber(planassign.getCaseNumber());
		entity.setCitizen(en);

		BeanUtils.copyProperties(planassign, entity);

		edurepo.save(entity);

		return "Education details saved successfully";
	}

	public String kidsDtls(Integer caseNumber, List<KidsBinding> kids) {

		CitizenEntity citzen = new CitizenEntity();

		citzen.setCaseNumber(caseNumber);

		for (KidsBinding kidBinding : kids) {
			
			KidEntity kidentity = new KidEntity();

			kidentity.setCitizen(citzen);
			
			BeanUtils.copyProperties(kidBinding, kidentity);
			
			kidrepo.save(kidentity);
		}

		return "kids Details saved successfully";

	}

}
