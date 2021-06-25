package com.svarks.trueherb.dao.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.svarks.trueherb.dao.entity.UserAuthInfo;

@Repository
public interface UserAuthDao extends JpaRepository<UserAuthInfo, Integer> {

	
	 boolean findByToken(@Param("token") String token );
	 void deleteAuthToken(@Param("token") String token );
	 
	 /*Query query = em.createQuery(
		      "SELECT c FROM Country c WHERE c.name = 'Canada'");
		  Country c = (Country)query.getSingleResult();*/
}
