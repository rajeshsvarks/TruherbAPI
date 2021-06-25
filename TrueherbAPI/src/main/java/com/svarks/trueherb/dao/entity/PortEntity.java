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
		@NamedQuery(name = "PortEntity.finByPortName", query = "SELECT e FROM PortEntity e WHERE e.fromCountry =:fromCountry "),
		@NamedQuery(name = "PortEntity.isPortExists", query = "SELECT CASE WHEN (COUNT(*) >0) THEN TRUE ELSE FALSE END FROM PortEntity"
				+ " e WHERE e.fromCountry =:fromCountry and e.toCountry=:toCountry and e.fromPort=:fromPort and toPort=:toPort ")})

public class PortEntity implements Serializable {

	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name="portid",updatable=false,nullable=false)
	private int portid;
	private String fromCountry;
	private String toCountry;
	private String fromPort;
	private String toPort;
	private Date createdDate;
	private Date modifiedDate;
	
	public int getPortid() {
		return portid;
	}
	public void setPortid(int portid) {
		this.portid = portid;
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
	public String getFromPort() {
		return fromPort;
	}
	public void setFromPort(String fromPort) {
		this.fromPort = fromPort;
	}
	public String getToPort() {
		return toPort;
	}
	public void setToPort(String toPort) {
		this.toPort = toPort;
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
