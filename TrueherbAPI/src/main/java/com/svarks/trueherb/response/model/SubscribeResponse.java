package com.svarks.trueherb.response.model;

import java.util.LinkedList;
import java.util.List;

import com.svarks.trueherb.dao.entity.SubscribeEntity;

public class SubscribeResponse extends BaseResponse{
	
	List<SubscribeEntity> sublit = new LinkedList<>();

	public List<SubscribeEntity> getSublit() {
		return sublit;
	}

	public void setSublit(List<SubscribeEntity> sublit) {
		this.sublit = sublit;
	}
}
