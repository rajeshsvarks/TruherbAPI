package com.svarks.trueherb.response.model;

import java.util.List;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public class UserListResponse extends BaseResponse{
	
	List<UsersProfileData> userProfileDataList;

	public List<UsersProfileData> getUserProfileDataList() {
		return userProfileDataList;
	}

	public void setUserProfileDataList(List<UsersProfileData> userProfileDataList) {
		this.userProfileDataList = userProfileDataList;
	}
	
	@Override
	public String toString() {
	     return ReflectionToStringBuilder.toString(this);
	 }	
}
