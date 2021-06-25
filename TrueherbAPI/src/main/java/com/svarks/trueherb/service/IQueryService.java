package com.svarks.trueherb.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.svarks.trueherb.response.model.UsersProfileData;

public interface IQueryService {

	public List<UsersProfileData> getUserInfoList();
}
