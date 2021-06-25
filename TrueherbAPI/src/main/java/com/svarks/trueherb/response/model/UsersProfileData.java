package com.svarks.trueherb.response.model;

import java.util.Date;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public class UsersProfileData {
	
	private int profileId;
	private String emailId;
    private String username;
    private int utype;
    private String firstname;
	private String lastname;
	private String mobilenumber;
	private String companyname;
	private String referalCode;
	private Date verifiedDate;
	
	public UsersProfileData(int profileId, String emailId, String username, int utype, 
			String firstname, String lastname,String mobilenumber, 
			String referalCode,String companyname,Date verifiedDate) {
		super();
		this.profileId = profileId;
		this.emailId = emailId;
		this.username = username;
		this.utype = utype;
		this.firstname = firstname;
		this.lastname = lastname;
		this.mobilenumber = mobilenumber;
		this.referalCode=referalCode;
		this.companyname=companyname;
		this.verifiedDate=verifiedDate;
	}
	
	public Date getVerifiedDate() {
		return verifiedDate;
	}


	public void setVerifiedDate(Date verifiedDate) {
		this.verifiedDate = verifiedDate;
	}


	public String getCompanyname() {
		return companyname;
	}
	public void setCompanyname(String companyname) {
		this.companyname = companyname;
	}
	public String getReferalCode() {
		return referalCode;
	}


	public void setReferalCode(String referalCode) {
		this.referalCode = referalCode;
	}


	public int getProfileId() {
		return profileId;
	}
	public void setProfileId(int profileId) {
		this.profileId = profileId;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getUtype() {
		return utype;
	}
	public void setUtype(int utype) {
		this.utype = utype;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getMobilenumber() {
		return mobilenumber;
	}
	public void setMobilenumber(String mobilenumber) {
		this.mobilenumber = mobilenumber;
	}
	
	@Override
	public String toString() {
	     return ReflectionToStringBuilder.toString(this);
	 }	

}
