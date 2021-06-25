package com.svarks.trueherb.dao.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.transaction.Transactional;

@Entity
@Transactional
@NamedQueries({
		@NamedQuery(name = "LegalEntity.finByEntityName", query = "SELECT e FROM LegalEntity e WHERE e.entityName =:entityName "),
		@NamedQuery(name = "LegalEntity.isLegalEntityExists", query = "SELECT CASE WHEN (COUNT(*) >0) THEN TRUE ELSE FALSE END FROM LegalEntity e WHERE e.entityName =:entityName")})

public class LegalEntity implements Serializable {

	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name="legalEntityId",updatable=false,nullable=false)
	private int legalEntityId;
	private String entityName;
	private String coutry;
	private String pincode;
	private Date createdDate;
	private Date modifiedDate;
	
	
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
	public int getLegalEntityId() {
		return legalEntityId;
	}
	public void setLegalEntityId(int legalEntityId) {
		this.legalEntityId = legalEntityId;
	}
	public String getEntityName() {
		return entityName;
	}
	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}
	public String getCoutry() {
		return coutry;
	}
	public void setCoutry(String coutry) {
		this.coutry = coutry;
	}
	public String getPincode() {
		return pincode;
	}
	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

}
