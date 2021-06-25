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
		@NamedQuery(name = "Batch.finByBatchName", query = "SELECT e FROM Batch e WHERE e.batchName =:batchName "),
		@NamedQuery(name = "Batch.isBatchExists", query = "SELECT CASE WHEN (COUNT(*) >0) THEN TRUE ELSE FALSE END FROM Batch e WHERE e.batchName =:batchName")})

public class Batch implements Serializable {

	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name="bid",updatable=false,nullable=false)
	private int bid;
	private String batchName;
	private Date createdDate;
	private Date modifiedDate;
	public int getBid() {
		return bid;
	}
	public void setBid(int bid) {
		this.bid = bid;
	}
	public String getBatchName() {
		return batchName;
	}
	public void setBatchName(String batchName) {
		this.batchName = batchName;
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
		return "Batch [bid=" + bid + ", batchName=" + batchName + ", createdDate=" + createdDate + ", modifiedDate="
				+ modifiedDate + "]";
	}

}
