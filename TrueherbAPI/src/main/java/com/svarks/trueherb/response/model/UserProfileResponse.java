package com.svarks.trueherb.response.model;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import com.svarks.trueherb.dao.entity.UserProfileEntity;

public class UserProfileResponse extends BaseResponse {
	
	private UserProfileEntity profileEntity;

	public UserProfileEntity getProfileEntity() {
		return profileEntity;
	}

	public void setProfileEntity(UserProfileEntity profileEntity) {
		this.profileEntity = profileEntity;
	}
	
	@Override
	public String toString() {
	     return ReflectionToStringBuilder.toString(this);
	 }	
}
