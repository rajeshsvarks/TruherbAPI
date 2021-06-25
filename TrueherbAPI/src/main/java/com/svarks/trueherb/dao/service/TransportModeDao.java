package com.svarks.trueherb.dao.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.svarks.trueherb.dao.entity.TransportMode;


public interface TransportModeDao extends JpaRepository<TransportMode, Integer>{
	
	boolean isTransportModeExists(@Param("fromCountry") String fromCountry,@Param("toCountry") String toCountry);
	TransportModeDao finByModeName(@Param("fromCountry") String fromCountry );


}