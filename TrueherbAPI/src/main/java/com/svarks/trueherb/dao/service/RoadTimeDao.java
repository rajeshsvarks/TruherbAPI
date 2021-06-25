package com.svarks.trueherb.dao.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.svarks.trueherb.dao.entity.RoadTimings;

public interface RoadTimeDao extends JpaRepository<RoadTimings, Integer>{
	
	boolean isRoadTimeExists(@Param("kilometer") Double kilometer);
	RoadTimings findByKilometer(@Param("kilometer") Double kilometer );


}
