package com.svarks.trueherb.response.model;

import java.util.List;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import com.svarks.trueherb.dao.entity.LegalEntity;

public class LegalEntityResponse extends BaseResponse {
	
	private List<LegalEntity> legalEntityList;

	public List<LegalEntity> getLegalEntityList() {
		return legalEntityList;
	}

	public void setLegalEntityList(List<LegalEntity> legalEntityList) {
		this.legalEntityList = legalEntityList;
	}
	
	@Override
	public String toString() {
	     return ReflectionToStringBuilder.toString(this);
	 }

}
