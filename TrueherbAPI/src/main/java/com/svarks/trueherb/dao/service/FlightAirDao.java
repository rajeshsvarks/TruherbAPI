package com.svarks.trueherb.dao.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.svarks.trueherb.dao.entity.FlightSeaTimings;
import com.svarks.trueherb.dao.entity.PortEntity;

public interface FlightAirDao extends JpaRepository<FlightSeaTimings, Integer> {

	boolean isTimingsExists(@Param("fromCountry") String fromCountry,@Param("toCountry") String toCountry,
			@Param("mode") String mode);
	PortEntity finByfromCountryName(@Param("fromCountry") String fromCountry );
}
