package com.svarks.trueherb.dao.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.transaction.Transactional;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

@Entity
@Transactional
@Table(name = "user_profile")
@NamedQueries({ 
		@NamedQuery(name = "UserProfileEntity.getUserProfileData", query = "SELECT u FROM UserProfileEntity u where u.companyname=:companyname "),
		@NamedQuery(name = "UserProfileEntity.getProfileDataByEmail", query = "SELECT u FROM UserProfileEntity u where u.companyEmailId=:companyEmailId "),
		@NamedQuery(name = "UserProfileEntity.changeEmail", query = "UPDATE UserProfileEntity e SET e.companyEmailId =:newEmailId,e.modifiedDate=now() where e.companyEmailId =:oldEmailId "),
		@NamedQuery(name = "UserProfileEntity.getAllUserDetails", query = "SELECT e FROM UserProfileEntity e where e.userType > 1 order by e.createdDate desc")
		
		 })

/*
 * 
 * @NamedQuery(name = "UserProfileEntity.updateProfile", query = "UPDATE UserProfileEntity p SET p.firstname=:firstname,"
				+ " p.customername=:lastname, p.address=:state, p.description=:city,p.phonenumber=:phonenumber,p.modifiedDate=now() where p.profileId=:pid AND p.uemailId=:pemailId")
 */
public class UserProfileEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int profileId;
	private String firstname;
	private String lastname;
	private String companyname;
	private String companyEmailId;
	private String mobilenumber;
	private String referalCode;
	private int userType;
	private Date createdDate;
	private Date modifiedDate;
	
	public int getProfileId() {
		return profileId;
	}

	public void setProfileId(int profileId) {
		this.profileId = profileId;
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

	public String getCompanyname() {
		return companyname;
	}

	public void setCompanyname(String companyname) {
		this.companyname = companyname;
	}

	public String getCompanyEmailId() {
		return companyEmailId;
	}

	public void setCompanyEmailId(String companyEmailId) {
		this.companyEmailId = companyEmailId;
	}

	public String getMobilenumber() {
		return mobilenumber;
	}

	public void setMobilenumber(String mobilenumber) {
		this.mobilenumber = mobilenumber;
	}

	public String getReferalCode() {
		return referalCode;
	}

	public void setReferalCode(String referalCode) {
		this.referalCode = referalCode;
	}

	public int getUserType() {
		return userType;
	}

	public void setUserType(int userType) {
		this.userType = userType;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}
}
