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
		@NamedQuery(name = "RoadTimings.findByKilometer", query = "SELECT e FROM RoadTimings e WHERE e.kilometer =:kilometer "),
		@NamedQuery(name = "RoadTimings.isRoadTimeExists", query = "SELECT CASE WHEN (COUNT(*) >0) THEN TRUE ELSE FALSE END FROM RoadTimings"
				+ " e WHERE e.kilometer =:kilometer")})

public class RoadTimings implements Serializable {

	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name="timingsId",updatable=false,nullable=false)
	private int timingsId;
	private Double kilometer;
	private Double timeInHrs;
	private Date createdDate;
	private Date modifiedDate;
	public int getTimingsId() {
		return timingsId;
	}
	public void setTimingsId(int timingsId) {
		this.timingsId = timingsId;
	}
	public Double getKilometer() {
		return kilometer;
	}
	public void setKilometer(Double kilometer) {
		this.kilometer = kilometer;
	}
	public Double getTimeInHrs() {
		return timeInHrs;
	}
	public void setTimeInHrs(Double timeInHrs) {
		this.timeInHrs = timeInHrs;
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
