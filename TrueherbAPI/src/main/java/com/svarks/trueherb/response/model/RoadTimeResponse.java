package com.svarks.trueherb.response.model;

import java.util.List;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import com.svarks.trueherb.dao.entity.RoadTimings;

public class RoadTimeResponse extends BaseResponse {
	
	private List<RoadTimings> roadTimeList;

	public List<RoadTimings> getRoadTimeList() {
		return roadTimeList;
	}

	public void setRoadTimeList(List<RoadTimings> roadTimeList) {
		this.roadTimeList = roadTimeList;
	}

	@Override
	public String toString() {
	     return ReflectionToStringBuilder.toString(this);
	 }	
}
