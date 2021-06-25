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
		@NamedQuery(name = "FlightSeaTimings.finByfromCountryName", query = "SELECT e FROM FlightSeaTimings e WHERE e.fromCountry =:fromCountry "),
		@NamedQuery(name = "FlightSeaTimings.isTimingsExists", query = "SELECT CASE WHEN (COUNT(*) >0) THEN TRUE ELSE FALSE END FROM FlightSeaTimings"
				+ " e WHERE e.fromCountry =:fromCountry and e.toCountry=:toCountry and e.mode=:mode ")})

public class FlightSeaTimings implements Serializable {

		
		private static final long serialVersionUID = 1L;
		@Id
		@GeneratedValue(strategy = GenerationType.SEQUENCE)
		@Column(name="flightSeaId",updatable=false,nullable=false)
		private int flightSeaId;
		private String fromCountry;
		private String toCountry;
		private Double airTime;
		private Double seaTime;
		private String mode;
		private Date createdDate;
		private Date modifiedDate;
		public int getFlightSeaId() {
			return flightSeaId;
		}
		public void setFlightSeaId(int flightSeaId) {
			this.flightSeaId = flightSeaId;
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
		public Double getAirTime() {
			return airTime;
		}
		public void setAirTime(Double airTime) {
			this.airTime = airTime;
		}
		public Double getSeaTime() {
			return seaTime;
		}
		public void setSeaTime(Double seaTime) {
			this.seaTime = seaTime;
		}
		public String getMode() {
			return mode;
		}
		public void setMode(String mode) {
			this.mode = mode;
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
