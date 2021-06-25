package com.svarks.trueherb.request.model;

public class ContactUSRequest {
	
	private String companyName;
	private String designation;
	private String phonenumber;
	private String emailid;
	private String websiteLink;
	private String budget;
	private String address;
	private String pincode;
	private String message;
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public String getPhonenumber() {
		return phonenumber;
	}
	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}
	public String getEmailid() {
		return emailid;
	}
	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}
	public String getWebsiteLink() {
		return websiteLink;
	}
	public void setWebsiteLink(String websiteLink) {
		this.websiteLink = websiteLink;
	}
	public String getBudget() {
		return budget;
	}
	public void setBudget(String budget) {
		this.budget = budget;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPincode() {
		return pincode;
	}
	public void setPincode(String pincode) {
		this.pincode = pincode;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	@Override
	public String toString() {
		return "ContactUSRequest [companyName=" + companyName + ", designation=" + designation + ", phonenumber="
				+ phonenumber + ", emailid=" + emailid + ", websiteLink=" + websiteLink + ", budget=" + budget
				+ ", address=" + address + ", pincode=" + pincode + ", message=" + message + "]";
	}

}
