package com.ashokit.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ashokit.entity.EligibleDetails;

public interface EligibleRepo extends JpaRepository<EligibleDetails, Integer>{
	

}
