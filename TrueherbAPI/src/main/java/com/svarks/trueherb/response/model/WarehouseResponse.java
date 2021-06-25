package com.svarks.trueherb.response.model;

import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import com.svarks.trueherb.dao.entity.Warehouse;

public class WarehouseResponse extends BaseResponse {
	
	private List<Warehouse> warhouseList =new LinkedList<>();

	public List<Warehouse> getWarhouseList() {
		return warhouseList;
	}

	public void setWarhouseList(List<Warehouse> warhouseList) {
		this.warhouseList = warhouseList;
	}
	
	@Override
	public String toString() {
	     return ReflectionToStringBuilder.toString(this);
	 }

}
