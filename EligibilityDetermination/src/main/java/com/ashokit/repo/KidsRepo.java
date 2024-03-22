package com.ashokit.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ashokit.entity.CitizenEntity;
import com.ashokit.entity.KidEntity;

public interface KidsRepo extends JpaRepository<KidEntity,Integer>{

	
	List<KidEntity> findByCitizen_CaseNumber(Integer caseNumber);
}
