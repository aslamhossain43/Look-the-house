package com.renu.look.house.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.renu.to_let.models.User;

public interface User_Repository extends JpaRepository<User,Long>,JpaSpecificationExecutor<User>{

	
	public User getByUsername(String username);
	@Modifying
	@Transactional
   @Query("UPDATE User SET password=:pwd WHERE username=:username")
	public void updatePassword(@Param("pwd") String pwd, @Param("username")String username);
	
	
	
	
}
