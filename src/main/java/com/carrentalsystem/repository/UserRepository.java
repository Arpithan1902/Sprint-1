package com.carrentalsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.carrentalsystem.entity.User;

public interface UserRepository extends JpaRepository<User, Integer>{

	
	@Query("select c from User c where c.email= :useremail and c.password= :passwd")
	User login(@Param("useremail")String loguserName, @Param("passwd") String password);
	
	

}
