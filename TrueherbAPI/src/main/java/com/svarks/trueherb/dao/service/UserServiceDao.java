package com.svarks.trueherb.dao.service;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.svarks.trueherb.dao.entity.UserEntity;

@Repository
public interface UserServiceDao extends JpaRepository<UserEntity, Integer>{

	
	 boolean findByUsername(@Param("emailId") String emailId );
	 boolean isValidUser(@Param("username") String username );
	 @Transactional
	 @Modifying
	 void updateIsEmailVerified(@Param("username") String username);
	 @Transactional
	 @Modifying
	 void updateEmailId(@Param("oldEmailId") String oldEmailId,@Param("newEmailId") String newEmailId,@Param("username") String username);
	 UserEntity getUserDataByUsername(@Param("username") String username);
	 UserEntity findUserByCredentials(@Param("username") String username, @Param("password") String password);
	 UserEntity findUserInfoByCred(@Param("username") String username, @Param("password") String password);
	 @Transactional
	 @Modifying
	 void resetNewPassword(@Param("username") String username, @Param("password") String password);
	// List<UserEntity> getUserByEmail(@Param("emailId") String emailId);
}
