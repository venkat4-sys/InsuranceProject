package com.ashokit.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ashokit.entity.CitizenEntity;
import com.ashokit.entity.EducationEntity;

public interface EducationRepo extends JpaRepository<EducationEntity,Integer> {

	
	EducationEntity findByCitizen_CaseNumber(Integer caseNumber);
}
