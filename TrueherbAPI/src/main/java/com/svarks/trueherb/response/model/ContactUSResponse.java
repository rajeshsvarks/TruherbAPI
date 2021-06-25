package com.svarks.trueherb.response.model;

import java.util.LinkedList;
import java.util.List;

import com.svarks.trueherb.dao.entity.ContactUSEntity;

public class ContactUSResponse extends BaseResponse {
	
	private List<ContactUSEntity>  cEntityList =new LinkedList<>();

	public List<ContactUSEntity> getcEntityList() {
		return cEntityList;
	}

	public void setcEntityList(List<ContactUSEntity> cEntityList) {
		this.cEntityList = cEntityList;
	}

	@Override
	public String toString() {
		return "ContactUSResponse [cEntityList=" + cEntityList + "]";
	}
	

}
