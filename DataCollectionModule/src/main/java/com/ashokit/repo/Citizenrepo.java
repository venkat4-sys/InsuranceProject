package com.ashokit.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ashokit.entity.CitizenEntity;

public interface Citizenrepo extends JpaRepository<CitizenEntity,Integer>{

	
	@Query(value ="SELECT max(case_number) FROM citizen_entity",nativeQuery=true)
    Integer getMaxSequenceNumber();
	
	
	CitizenEntity findByCaseNumber(Integer caseNumber);
	
	
}
