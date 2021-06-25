package com.svarks.trueherb.response.model;

import java.util.List;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.springframework.http.ResponseEntity;

import com.svarks.trueherb.dao.entity.Products;

public class ProductListResponse extends BaseResponse{
	
	private String  zohoResponse;

	public String getZohoResponse() {
		return zohoResponse;
	}

	public void setZohoResponse(String zohoResponse) {
		this.zohoResponse = zohoResponse;
	}

	@Override
	public String toString() {
	     return ReflectionToStringBuilder.toString(this);
	 }

}
