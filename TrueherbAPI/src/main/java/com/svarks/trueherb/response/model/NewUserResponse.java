package com.svarks.trueherb.response.model;

import com.svarks.trueherb.dao.entity.UserProfileEntity;

public class NewUserResponse extends BaseResponse {
	
	private UserProfileEntity userProfile = new UserProfileEntity();

	public UserProfileEntity getUserProfile() {
		return userProfile;
	}

	public void setUserProfile(UserProfileEntity userProfile) {
		this.userProfile = userProfile;
	}

	@Override
	public String toString() {
		return "NewUserResponse [userProfile=" + userProfile + "]";
	}
	
}
