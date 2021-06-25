package com.svarks.trueherb.request.model;

public class SubscribeRequest {
	
	private String name;
	private String emailId;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	@Override
	public String toString() {
		return "SubscribeRequest [name=" + name + ", emailId=" + emailId + "]";
	}
}
