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
@Table(name = "users")
@NamedQueries({
		@NamedQuery(name = "UserEntity.findByUsername", query = "SELECT CASE WHEN (COUNT(*) >0) THEN TRUE ELSE FALSE END FROM UserEntity e WHERE e.emailId =:emailId "),
		@NamedQuery(name = "UserEntity.isValidUser", query = "SELECT CASE WHEN (COUNT(*) >0) THEN TRUE ELSE FALSE END FROM UserEntity e WHERE e.username =:username"),
		@NamedQuery(name = "UserEntity.updateIsEmailVerified", query = "UPDATE UserEntity e SET e.isEmailVerified =1,e.verifiedDate=now() where e.username =:username "),
		@NamedQuery(name = "UserEntity.updateEmailId", query = "UPDATE UserEntity e SET e.emailId =:newEmailId,e.username =:username,e.modifiedDate=now() where e.emailId =:oldEmailId "),
		@NamedQuery(name = "UserEntity.findUserByCredentials", query = "SELECT e FROM UserEntity e WHERE e.username =:username AND e.password = :password AND e.isEmailVerified=true"),
		@NamedQuery(name = "UserEntity.findUserInfoByCred", query = "SELECT e FROM UserEntity e WHERE e.username =:username AND e.password = :password"),
		@NamedQuery(name = "UserEntity.getUserDataByUsername", query = "SELECT e FROM UserEntity e WHERE e.username =:username"),
		@NamedQuery(name = "UserEntity.resetNewPassword", query = "UPDATE UserEntity e SET e.password =:password,e.isFirstTimeLogin=0 where e.username =:username ") })
//
public class UserEntity implements Serializable {

	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int uid;
    private String emailId;
    private String username;
    private String token;
    private String password;
    private int utype;
    private boolean isEmailVerified;
    private Date createdDate;
    private Date modifiedDate;
    private Date verifiedDate;
    private boolean isFirstTimeLogin;
    
	public Date getVerifiedDate() {
		return verifiedDate;
	}
	public void setVerifiedDate(Date verifiedDate) {
		this.verifiedDate = verifiedDate;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getUtype() {
		return utype;
	}
	public void setUtype(int utype) {
		this.utype = utype;
	}
	public boolean isEmailVerified() {
		return isEmailVerified;
	}
	public void setEmailVerified(boolean isEmailVerified) {
		this.isEmailVerified = isEmailVerified;
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
	public boolean isFirstTimeLogin() {
		return isFirstTimeLogin;
	}
	public void setFirstTimeLogin(boolean isFirstTimeLogin) {
		this.isFirstTimeLogin = isFirstTimeLogin;
	}
	@Override
	public String toString() {
	     return ReflectionToStringBuilder.toString(this);
	 }

}
