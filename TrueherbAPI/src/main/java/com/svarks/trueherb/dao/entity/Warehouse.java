package com.svarks.trueherb.dao.entity;

import java.io.Serializable;

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
@Table(name = "warehouse")
@NamedQueries({
		@NamedQuery(name = "Warehouse.getWarehouseByName", query = "SELECT e FROM Warehouse e WHERE e.warehouseName =:warehouseName "),
		@NamedQuery(name = "Warehouse.findByWarehouse", query = "SELECT CASE WHEN (COUNT(*) >0) THEN TRUE ELSE FALSE END FROM Warehouse e WHERE e.warehouseName =:warehouseName ")})

public class Warehouse implements Serializable {

	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int wid;
	private String warehouseName;
	private String address;
	private int pincode;
	public int getWid() {
		return wid;
	}
	public void setWid(int wid) {
		this.wid = wid;
	}
	public String getWarehouseName() {
		return warehouseName;
	}
	public void setWarehouseName(String warehouseName) {
		this.warehouseName = warehouseName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getPincode() {
		return pincode;
	}
	public void setPincode(int pincode) {
		this.pincode = pincode;
	}
	
	@Override
	public String toString() {
	     return ReflectionToStringBuilder.toString(this);
	 }
	
}
