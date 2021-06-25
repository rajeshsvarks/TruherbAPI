package com.svarks.trueherb.request.model;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public class PortRequest {
	
	private int portid;
	private String fromCountry;
	private String toCountry;
	private String fromPort;
	private String toPort;
	private boolean isDelete;
	public int getPortid() {
		return portid;
	}
	public void setPortid(int portid) {
		this.portid = portid;
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
	public String getFromPort() {
		return fromPort;
	}
	public void setFromPort(String fromPort) {
		this.fromPort = fromPort;
	}
	public String getToPort() {
		return toPort;
	}
	public void setToPort(String toPort) {
		this.toPort = toPort;
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
