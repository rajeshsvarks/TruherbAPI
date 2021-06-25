package com.svarks.trueherb.response.model;

import java.util.List;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import com.svarks.trueherb.dao.entity.PortEntity;

public class PortResponse extends BaseResponse{
	
	private List<PortEntity> portEntityList;

	public List<PortEntity> getPortEntityList() {
		return portEntityList;
	}

	public void setPortEntityList(List<PortEntity> portEntityList) {
		this.portEntityList = portEntityList;
	}
	
	@Override
	public String toString() {
	     return ReflectionToStringBuilder.toString(this);
	 }
	
}
