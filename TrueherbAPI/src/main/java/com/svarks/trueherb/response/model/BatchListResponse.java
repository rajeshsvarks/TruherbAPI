package com.svarks.trueherb.response.model;

import java.util.List;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import com.svarks.trueherb.dao.entity.Batch;

public class BatchListResponse extends BaseResponse{
	
	List<Batch> batchList;

	public List<Batch> getBatchList() {
		return batchList;
	}

	public void setBatchList(List<Batch> batchList) {
		this.batchList = batchList;
	}
	
	@Override
	public String toString() {
	     return ReflectionToStringBuilder.toString(this);
	 }

}
