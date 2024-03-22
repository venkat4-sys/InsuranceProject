package com.ashokit.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ashokit.entity.CitizenEntity;
import com.ashokit.entity.IncomeEntity;

public interface IncomeRepo extends JpaRepository<IncomeEntity,Integer>{

	IncomeEntity findByCitizen_CaseNumber(Integer caseNumber);

}
