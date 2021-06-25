package com.svarks.trueherb.service;

import java.util.Date;

import com.svarks.trueherb.request.model.MailerRequest;
import com.svarks.trueherb.response.model.BaseResponse;



public interface TrueherbWareshouseService {

	public void sendMail(MailerRequest mailRequest);
	public BaseResponse generateResponse(boolean isSuccess,String message);
	public Date convertStringToDate(String date);
}
