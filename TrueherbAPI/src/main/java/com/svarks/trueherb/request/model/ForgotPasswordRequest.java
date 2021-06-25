package com.svarks.trueherb.request.model;

public class ForgotPasswordRequest {
	
	private String usename;

	public String getUsename() {
		return usename;
	}

	public void setUsename(String usename) {
		this.usename = usename;
	}

	@Override
	public String toString() {
		return "ForgotPasswordRequest [usename=" + usename + "]";
	};

}
