package com.svarks.trueherb.response.model;

import java.util.List;

import com.svarks.trueherb.dao.entity.EnquiryEntity;

public class EnquiryResponse extends BaseResponse {
	
	private List<EnquiryEntity> enquiryList;

	public List<EnquiryEntity> getEnquiryList() {
		return enquiryList;
	}

	public void setEnquiryList(List<EnquiryEntity> enquiryList) {
		this.enquiryList = enquiryList;
	}

	@Override
	public String toString() {
		return "EnquiryResponse [enquiryList=" + enquiryList + "]";
	}
	
}
