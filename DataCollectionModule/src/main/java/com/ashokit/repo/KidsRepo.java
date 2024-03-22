package com.ashokit.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ashokit.entity.KidEntity;

public interface KidsRepo extends JpaRepository<KidEntity,Integer>{

}
