package com.ashokit.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ashokit.entity.PlansEntity;

public interface PlanRepo extends JpaRepository<PlansEntity,Integer>{

	
}
