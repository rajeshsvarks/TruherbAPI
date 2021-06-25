package com.svarks.trueherb.request.model;

public class ChangeEmailRequest {
	
	private String oldEmailId;
	private String newEmailID;
	private String name;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getOldEmailId() {
		return oldEmailId;
	}
	public void setOldEmailId(String oldEmailId) {
		this.oldEmailId = oldEmailId;
	}
	public String getNewEmailID() {
		return newEmailID;
	}
	public void setNewEmailID(String newEmailID) {
		this.newEmailID = newEmailID;
	}
	
}
