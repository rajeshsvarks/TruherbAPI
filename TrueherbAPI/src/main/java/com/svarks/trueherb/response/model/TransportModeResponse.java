package com.svarks.trueherb.response.model;

import java.util.List;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import com.svarks.trueherb.dao.entity.TransportMode;

public class TransportModeResponse extends BaseResponse {

	private List<TransportMode> transportModeList;

	public List<TransportMode> getTransportModeList() {
		return transportModeList;
	}

	public void setTransportModeList(List<TransportMode> transportModeList) {
		this.transportModeList = transportModeList;
	}
	
	@Override
	public String toString() {
	     return ReflectionToStringBuilder.toString(this);
	 }	
}
