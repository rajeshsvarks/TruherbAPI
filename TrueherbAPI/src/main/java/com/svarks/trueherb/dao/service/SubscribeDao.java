package com.svarks.trueherb.dao.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.svarks.trueherb.dao.entity.SubscribeEntity;

public interface SubscribeDao extends JpaRepository<SubscribeEntity, Integer>{

	boolean isEmailExists(@Param("emailId") String emailId);
	SubscribeEntity finByEmail(@Param("emailId") String emailId );

}
