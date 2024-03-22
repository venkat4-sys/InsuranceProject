package com.ashokit.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import com.ashokit.entity.User;

public interface UserRepo extends JpaRepository<User,Integer>{
	
	public User findByEmailAndPassword(String email,String password);
	
	public User findByEmail(String email);
	
	@Query(value="select * from User",nativeQuery = true)
	public List<User> getAllWorks();

	
	

}
