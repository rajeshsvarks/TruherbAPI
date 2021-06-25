package com.svarks.trueherb.dao.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.svarks.trueherb.dao.entity.PortEntity;

public interface PortEntityDao extends JpaRepository<PortEntity, Integer> {

	boolean isPortExists(@Param("fromCountry") String fromCountry,@Param("toCountry") String toCountry,
			@Param("fromPort") String fromPort,@Param("toPort") String toPort);
	PortEntity finByPortName(@Param("fromCountry") String fromCountry );
}
