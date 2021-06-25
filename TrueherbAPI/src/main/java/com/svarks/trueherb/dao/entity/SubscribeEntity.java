package com.svarks.trueherb.dao.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.transaction.Transactional;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@Transactional
@NamedQueries({
		@NamedQuery(name = "SubscribeEntity.finByEmail", query = "SELECT e FROM SubscribeEntity e WHERE e.emailId =:emailId "),
		@NamedQuery(name = "SubscribeEntity.isEmailExists", query = "SELECT CASE WHEN (COUNT(*) >0) THEN TRUE ELSE FALSE END FROM SubscribeEntity e WHERE e.emailId =:emailId")})

public class SubscribeEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name="sid",updatable=false,nullable=false)
	private int sid;
	private String name;
	private String emailId;
	private Date createdDate;
	public int getSid() {
		return sid;
	}
	public void setSid(int sid) {
		this.sid = sid;
	}
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
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
}
