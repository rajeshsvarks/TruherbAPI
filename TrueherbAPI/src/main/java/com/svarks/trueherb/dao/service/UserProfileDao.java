package com.svarks.trueherb.dao.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.svarks.trueherb.dao.entity.UserProfileEntity;


@Repository
public interface UserProfileDao extends JpaRepository<UserProfileEntity, Integer> {

	UserProfileEntity getUserProfileData(@Param("companyname") String companyname);
	
	//UserProfileEntity getProfileByUser(@Param("username") String username);
	
	UserProfileEntity getProfileDataByEmail(@Param("companyEmailId") String companyEmailId);
	List<UserProfileEntity> getAllUserDetails();
	
	 @Transactional
	 @Modifying
	 void changeEmail(@Param("oldEmailId") String oldEmailId,@Param("newEmailId") String newEmailId);
	/*
	@Transactional
	@Modifying
	void updateProfile(@Param("firstname") String firstname, @Param("lastname") String lastname,
			@Param("state") String state, @Param("city") String city, @Param("phonenumber") String phonenumber,
			@Param("pid") int pid,@Param("pemailId") String emailId);*/
	
}
