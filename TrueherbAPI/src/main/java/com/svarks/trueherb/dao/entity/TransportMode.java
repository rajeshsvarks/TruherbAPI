package com.svarks.trueherb.dao.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Optional;

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
		@NamedQuery(name = "TransportMode.finByModeName", query = "SELECT e FROM TransportMode e WHERE e.fromCountry =:fromCountry "),
		@NamedQuery(name = "TransportMode.isTransportModeExists", query = "SELECT CASE WHEN (COUNT(*) >0) THEN TRUE ELSE FALSE END FROM TransportMode"
				+ " e WHERE e.fromCountry =:fromCountry and e.toCountry =:toCountry")})

public class TransportMode implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name="modeid",updatable=false,nullable=false)
	private int modeid;
	private String fromCountry;
	private String toCountry;
	private String modeType;  
	private Date createdDate;
	private Date modifiedDate;
	
	public int getModeid() {
		return modeid;
	}
	public void setModeid(int modeid) {
		this.modeid = modeid;
	}
	public String getFromCountry() {
		return fromCountry;
	}
	public void setFromCountry(String fromCountry) {
		this.fromCountry = fromCountry;
	}
	public String getToCountry() {
		return toCountry;
	}
	public void setToCountry(String toCountry) {
		this.toCountry = toCountry;
	}
	public String getModeType() {
		return modeType;
	}
	public void setModeType(String modeType) {
		this.modeType = modeType;
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
}
