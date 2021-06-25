package com.svarks.trueherb.response.model;

import java.util.List;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import com.svarks.trueherb.dao.entity.FlightSeaTimings;

public class FlightSeaResponse extends BaseResponse{
	
	private List<FlightSeaTimings> flightSeaTimingsList;

	public List<FlightSeaTimings> getFlightSeaTimingsList() {
		return flightSeaTimingsList;
	}

	public void setFlightSeaTimingsList(List<FlightSeaTimings> flightSeaTimingsList) {
		this.flightSeaTimingsList = flightSeaTimingsList;
	}
	
	@Override
	public String toString() {
	     return ReflectionToStringBuilder.toString(this);
	 }

}
