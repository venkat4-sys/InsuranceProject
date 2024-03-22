package com.ashokit.repo;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ashokit.entity.UserRoleEntity;

public interface UserRoleRepo extends JpaRepository<UserRoleEntity,Integer>{
	
	@Query(value="select * from user_role_entity where role_name = 'User'",nativeQuery=true)
	public List<UserRoleEntity> getAllWorkers();

}
