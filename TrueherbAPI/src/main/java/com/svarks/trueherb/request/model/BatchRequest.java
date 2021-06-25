package com.svarks.trueherb.request.model;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public class BatchRequest {

	private int bid;
	private String batchName;
	private boolean isDelete;
	
	public boolean isDelete() {
		return isDelete;
	}
	public void setIsDelete(boolean isDelete) {
		this.isDelete = isDelete;
	}
	public int getBid() {
		return bid;
	}
	public void setBid(int bid) {
		this.bid = bid;
	}
	public String getBatchName() {
		return batchName;
	}
	public void setBatchName(String batchName) {
		this.batchName = batchName;
	}
	
	@Override
	public String toString() {
	     return ReflectionToStringBuilder.toString(this);
	 }
}
