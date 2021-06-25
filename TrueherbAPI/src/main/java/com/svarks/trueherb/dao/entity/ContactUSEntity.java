package com.svarks.trueherb.dao.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.transaction.Transactional;

@Entity
@Transactional
//@NamedQueries({
//		@NamedQuery(name = "Batch.finByBatchName", query = "SELECT e FROM Batch e WHERE e.batchName =:batchName "),
//		@NamedQuery(name = "Batch.isBatchExists", query = "SELECT CASE WHEN (COUNT(*) >0) THEN TRUE ELSE FALSE END FROM Batch e WHERE e.batchName =:batchName")})

public class ContactUSEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name="cid",updatable=false,nullable=false)
	private int cid;
	private String companyName;
	private String designation;
	private String phonenumber;
	private String emailid;
	private String websiteLink;
	private String budget;
	private String address;
	private String pincode;
	private String message;
	private Date createdDate;
	
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
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
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	@Override
	public String toString() {
		return "ContactUSEntity [cid=" + cid + ", companyName=" + companyName + ", designation=" + designation
				+ ", phonenumber=" + phonenumber + ", emailid=" + emailid + ", websiteLink=" + websiteLink + ", budget="
				+ budget + ", address=" + address + ", pincode=" + pincode + ", message=" + message + "]";
	}
	
}
