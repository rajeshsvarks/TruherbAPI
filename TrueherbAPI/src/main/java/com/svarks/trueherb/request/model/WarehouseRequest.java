package com.svarks.trueherb.request.model;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public class WarehouseRequest {
	
	private int wid;
	private String warehouseName;
	private String address;
	private int pincode;
	
	
	
	public int getWid() {
		return wid;
	}
	public void setWid(int wid) {
		this.wid = wid;
	}
	public String getWarehouseName() {
		return warehouseName;
	}
	public void setWarehouseName(String warehouseName) {
		this.warehouseName = warehouseName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getPincode() {
		return pincode;
	}
	public void setPincode(int pincode) {
		this.pincode = pincode;
	}
	

	@Override
	public String toString() {
	     return ReflectionToStringBuilder.toString(this);
	 }

}
