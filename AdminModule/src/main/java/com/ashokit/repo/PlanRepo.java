package com.ashokit.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ashokit.entity.PlansEntity;

public interface PlanRepo extends JpaRepository<PlansEntity,Integer>{

	@Query(value="select * from PLANS_TBL",nativeQuery = true)
	public List<PlansEntity> getAllPlans();
}
