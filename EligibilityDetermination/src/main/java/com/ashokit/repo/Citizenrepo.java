package com.ashokit.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ashokit.entity.CitizenEntity;

public interface Citizenrepo extends JpaRepository<CitizenEntity,Integer>{

	CitizenEntity findByCaseNumber(Integer caseNumber);
	
	
	
}
