package com.svarks.trueherb.request.model;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public class ModeTimingsRequest {
	
	private int flightSeaId;
	private String fromCountry;
	private String toCountry;
	private Double airTime;
	private Double seaTime;
	private String mode;
	private boolean isDelete;
	
	public int getFlightSeaId() {
		return flightSeaId;
	}
	public void setFlightSeaId(int flightSeaId) {
		this.flightSeaId = flightSeaId;
	}
	public String getFromCountry() {
		return fromCountry;
	}
	public void setFromCountry(String fromCountry) {
		this.fromCountry = fromCountry;
	}
	public String getToCountry() {
		return toCountry;
	}
	public void setToCountry(String toCountry) {
		this.toCountry = toCountry;
	}
	public Double getAirTime() {
		return airTime;
	}
	public void setAirTime(Double airTime) {
		this.airTime = airTime;
	}
	public Double getSeaTime() {
		return seaTime;
	}
	public void setSeaTime(Double seaTime) {
		this.seaTime = seaTime;
	}
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
	public boolean isDelete() {
		return isDelete;
	}
	public void setIsDelete(boolean isDelete) {
		this.isDelete = isDelete;
	}
	
	@Override
	public String toString() {
	     return ReflectionToStringBuilder.toString(this);
	 }
}
