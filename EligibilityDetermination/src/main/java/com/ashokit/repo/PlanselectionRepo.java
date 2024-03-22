package com.ashokit.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ashokit.entity.PlanSelectionEntity;

public interface PlanselectionRepo extends JpaRepository<PlanSelectionEntity,Integer> {

	
	PlanSelectionEntity findByCaseNumber(Integer caseNumber);
}
